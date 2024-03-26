import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("fabric-loom") version "1.3.8"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.serialization") version "1.9.22"
    id("org.teamvoided.iridium") version "3.1.9"
}

group = project.properties["maven_group"]!!
version = project.properties["mod_version"]!!
base.archivesName.set(project.properties["archives_base_name"] as String)
description = ">B("
val modid = project.properties["modid"]!! as String

repositories {
    mavenCentral()
}

modSettings {
    modId(modid)
    modName("Dusk Autumns Blocks")

    entrypoint("main", "org.teamvoided.dusk_autumn.DuskAutumns::commonInit")
    entrypoint("client", "org.teamvoided.dusk_autumn.DuskAutumns::clientInit")
    entrypoint("fabric-datagen", "org.teamvoided.dusk_autumn.datagen.DuskAutumnsData")
    mixinFile("dusk_autumn.mixins.json")
    accessWidener("dusk_autumn.accesswidener")
}

loom {
    runs {
        // This adds a new gradle task that runs the datagen API: "gradlew runDatagen"
        create("DataGen") {
            client()
            ideConfigGenerated(true)
            vmArg("-Dfabric-api.datagen")
            vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
            vmArg("-Dfabric-api.datagen.modid=${modid}")
            runDir("build/datagen")
        }
    }
}

tasks {
    val targetJavaVersion = 17
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = targetJavaVersion.toString()
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
}

sourceSets["main"].resources.srcDir("src/main/generated")