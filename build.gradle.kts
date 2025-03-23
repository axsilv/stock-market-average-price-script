plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "2.1.20-Beta1"
    id("org.jmailen.kotlinter") version "5.0.1"
    id("org.jetbrains.kotlin.plugin.serialization") version "2.1.20-Beta1"
}

group = "stock.market.average.price.script"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.poi:poi-ooxml:5.2.3")
    implementation("org.apache.logging.log4j:log4j-slf4j2-impl:2.20.0")
    implementation("org.slf4j:slf4j-api:2.1.0-alpha1")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.8.0-RC")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.1.20-RC")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}