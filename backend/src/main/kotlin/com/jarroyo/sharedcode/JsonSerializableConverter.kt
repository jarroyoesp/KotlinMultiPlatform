package org.example.kotlin.multiplatform.backend

import io.ktor.application.ApplicationCall
import io.ktor.application.call
import io.ktor.content.TextContent
import io.ktor.features.ContentConverter
import io.ktor.features.ContentNegotiation
import io.ktor.features.suitableCharset
import io.ktor.http.ContentType
import io.ktor.http.charset
import io.ktor.http.withCharset
import io.ktor.request.ApplicationReceiveRequest
import io.ktor.request.contentType
import io.ktor.util.KtorExperimentalAPI
import io.ktor.util.pipeline.PipelineContext
import kotlinx.coroutines.io.ByteReadChannel
import kotlinx.coroutines.io.jvm.javaio.toInputStream
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.internal.ByteSerializer
import kotlinx.serialization.internal.CharSerializer
import kotlinx.serialization.internal.DoubleSerializer
import kotlinx.serialization.internal.FloatSerializer
import kotlinx.serialization.internal.IntSerializer
import kotlinx.serialization.internal.LongSerializer
import kotlinx.serialization.internal.ShortSerializer
import kotlinx.serialization.internal.StringSerializer
import kotlinx.serialization.internal.UnitSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.collections.set
import kotlin.reflect.KClass

/**
 * https://github.com/ktorio/ktor/pull/663
 */
@UseExperimental(KtorExperimentalAPI::class, UnstableDefault::class)
object JsonSerializableConverter : ContentConverter {
    private val json = Json.plain

    @Suppress("UNCHECKED_CAST")
    private val serializers = mutableMapOf(
        Unit::class as KClass<Any> to UnitSerializer as KSerializer<Any>,
        Boolean::class as KClass<Any> to BooleanSerializer as KSerializer<Any>,
        Byte::class as KClass<Any> to ByteSerializer as KSerializer<Any>,
        Short::class as KClass<Any> to ShortSerializer as KSerializer<Any>,
        Int::class as KClass<Any> to IntSerializer as KSerializer<Any>,
        Long::class as KClass<Any> to LongSerializer as KSerializer<Any>,
        Float::class as KClass<Any> to FloatSerializer as KSerializer<Any>,
        Double::class as KClass<Any> to DoubleSerializer as KSerializer<Any>,
        Char::class as KClass<Any> to CharSerializer as KSerializer<Any>,
        String::class as KClass<Any> to StringSerializer as KSerializer<Any>
    )

    /** Register objects of [type] to be serialized using [serializer]. */
    fun <T : Any> register(type: KClass<T>, serializer: KSerializer<T>) {
        @Suppress("UNCHECKED_CAST")
        serializers[type as KClass<Any>] = serializer as KSerializer<Any>
    }

    /** Register objects of type [T] to be serialized using [serializer]. */
    inline fun <reified T : Any> register(serializer: KSerializer<T>) {
        register(T::class, serializer)
    }

    /** Register objects of type [T] to be serialized. */
    @ImplicitReflectionSerializer
    inline fun <reified T : Any> register() {
        register(T::class.serializer())
    }

    private fun getSerializer(type: KClass<*>): KSerializer<Any>? = serializers[type]

    override suspend fun convertForReceive(context: PipelineContext<ApplicationReceiveRequest, ApplicationCall>): Any? {
        val subject = context.subject
        val input = subject.value as? ByteReadChannel
        val serializer = getSerializer(subject.type)
        if (input == null || serializer == null) return null

        val contentType = context.call.request.contentType()
        val text = input.toInputStream().reader(contentType.charset() ?: Charsets.UTF_8).readText()
        return json.parse(serializer, text)
    }

    override suspend fun convertForSend(
        context: PipelineContext<Any, ApplicationCall>,
        contentType: ContentType,
        value: Any
    ): Any? {
        val serializer = getSerializer(value::class)
        val text = serializer?.let { json.stringify(it, value) } ?: return null
        return TextContent(
            text = text,
            contentType = ContentType.Application.Json.withCharset(context.call.suitableCharset())
        )
    }
}

inline fun ContentNegotiation.Configuration.serializable(block: JsonSerializableConverter.() -> Unit) {
    register(ContentType.Application.Json, JsonSerializableConverter.apply(block))
}
