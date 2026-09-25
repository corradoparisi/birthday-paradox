plugins {
    java
    application
}

group = "ch.tbz"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.xchart)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
    testRuntimeOnly(libs.junit.platform.launcher)
}

application {
    // Run another simulation with: ./gradlew run -PmainClass=ch.tbz.BirthdayProbabilitiesPlot
    mainClass = providers.gradleProperty("mainClass").orElse("ch.tbz.BirthdayProbabilities")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.compilerArgs.addAll(listOf("-Xlint:all", "-Werror"))
}

tasks.test {
    useJUnitPlatform()
}
