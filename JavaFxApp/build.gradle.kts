plugins {
    id("application")
    id("java")
    id("kotlin")
}

application {
    mainClassName = "com.jarroyo.sharedcode.Main"
}

dependencies {
    // kotlin jdk
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50")
    implementation(project(":SharedCode"))
    //implementation("com.bugsnag:bugsnag:${Versions.bugsnagJvm}")
}
