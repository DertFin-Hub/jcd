plugins {
    id("java")
    application
}

group = "ru.dfhub"
version = "1.0"

tasks.jar {
    manifest.attributes["Main-Class"] = "ru.dfhub.Main"
    archiveFileName = "jcd.jar"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}