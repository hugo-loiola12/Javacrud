plugins {
    java
    application
}

group = "br.dev.projeto"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    // PostgreSQL JDBC Driver
    implementation("org.postgresql:postgresql:42.6.0")
}

application {
    mainClass.set("br.dev.projeto.crud.Main")
}

tasks.jar {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "br.dev.projeto.crud.App"
            )
        )
    }
}

// Tarefa para criar um jar com todas as dependências
tasks.register<Jar>("fatJar") {
    archiveBaseName.set("${project.name}-with-dependencies")
    manifest {
        attributes(
            mapOf(
                "Main-Class" to "br.dev.projeto.crud.App"
            )
        )
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    with(tasks.jar.get())
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}