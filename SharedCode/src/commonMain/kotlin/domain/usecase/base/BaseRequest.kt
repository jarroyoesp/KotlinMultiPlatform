package domain.usecase.base

interface BaseRequest {
    fun validate(): Boolean
}
