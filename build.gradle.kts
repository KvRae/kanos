plugins {
    kotlin("jvm") version "1.9.23"
    `java-gradle-plugin`
    `maven-publish`
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri("https://your-repo-url")
            credentials {
                username = project.findProperty("repoUser") as String? ?: ""
                password = project.findProperty("repoPassword") as String? ?: ""
            }
        }
    }
}

group = "com.kvrae"
version = "0.1.0-DEV"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation(kotlin("stdlib"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(11)
}


