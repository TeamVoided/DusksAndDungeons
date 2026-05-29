@file:Suppress("LocalVariableName", "VariableNaming")

import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.iridium)
    alias(libs.plugins.iridium.publish)
    alias(libs.plugins.iridium.upload)
}

base.archivesName.set(modSettings.modId())


repositories {
    maven("https://teamvoided.org/releases")
    maven("https://teamvoided.org/snapshots")
    maven("https://api.modrinth.com/maven")
    maven("https://maven.terraformersmc.com/") { name = "TerraformersMC" }
    maven("https://maven.fzzyhmstrs.me/") { name = "FzzyMaven" }
    exclusiveContent { // ImGuiMC
        forRepository {
            maven("https://maven.ryanhcode.dev/releases") { name = "RyanHCode Maven" }
        }
        filter {
            includeGroup("foundry.imguimc")
        }
    }
    mavenCentral()
}

modSettings {
    entrypoint("main", "org.teamvoided.dusks_and_dungeons.DusksAndDungeons::init")
    entrypoint("client", "org.teamvoided.dusks_and_dungeons.DusksAndDungeonsClient::init")
    entrypoint("fabric-datagen", "org.teamvoided.dusks_and_dungeons.data.gen.DnDData")
    entrypoint("emi", "org.teamvoided.dusks_and_dungeons.compat.DnDEmiPlugin")

    mixinFile("dusks_and_dungeons.mixins.json")
    mixinFile("dusks_and_dungeons.client.mixins.json")
    accessWidener("dusks_and_dungeons.accesswidener")
}

dependencies {
    modImplementation(fileTree("libs"))

    modImplementation(libs.biolith)
    include(libs.biolith)

    modImplementation(libs.reef)
    include(libs.reef)

    modImplementation(libs.voidmill)
    include(libs.voidmill)

    modImplementation(libs.taglighting)

    // Dev
    modImplementation(libs.modmenu)

    modCompileOnly("${libs.emi.get()}:api")
    modLocalRuntime(libs.emi)

    modImplementation(libs.bundles.cw)
    modCompileOnly("maven.modrinth:not-enough-animations:kaNdqksl")
}

loom {
    runs {
        splitEnvironmentSourceSets()
        runs {
            create("DataGen") {
                client()
                ideConfigGenerated(true)
                vmArg("-Dfabric-api.datagen")
                vmArg("-Dfabric-api.datagen.output-dir=${file("src/main/generated")}")
                vmArg("-Dfabric-api.datagen.modid=${modSettings.modId()}")
                runDir("build/datagen")
            }

            create("TestWorld") {
                client()
                ideConfigGenerated(true)
                runDir("run")
                programArgs("--quickPlaySingleplayer", "test")
            }
        }
        forEach {
            it.vmArgs(
                // If enabled this you can hotswap basally anything
                // Requires a JetBrains runtime!
                "-XX:+AllowEnhancedClassRedefinition",
                // If enabled this you can hotswap mixins
                // Requires you to add MIXIN_PATH to your .env file
                // Here is how to find the path: https://docs.fabricmc.net/develop/getting-started/intellij-idea/launching-the-game#1-locate-the-mixin-library-jar
//                "-javaagent:${System.getProperty("MIXIN_PATH")}"
            )
        }
    }
}
sourceSets["main"].resources.srcDir("src/main/generated")

tasks {
    val targetJavaVersion = 21
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(targetJavaVersion)
    }

    withType<KotlinCompile>().all {
        compilerOptions.jvmTarget = JvmTarget.JVM_21
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(JavaVersion.toVersion(targetJavaVersion).toString()))
        withSourcesJar()
    }
    jar {
        val valTaskNames = gradle.startParameter.taskNames
        if (!valTaskNames.contains("runDataGen")) {
//            exclude("org/teamvoided/dusks_and_dungeons/data/gen/*")
        } else {
            println("Running datagen for task ${valTaskNames.joinToString(" ")}")
        }
    }
}

publishScript {
    releaseRepository("TeamVoided", "https://maven.teamvoided.org/releases")
    publication("DusksAndDungeons", isSnapshot = false)
    publishSources(true)
}

/*uploadConfig {
//    debugMode = true
    modrinthId = modrinth_id
    curseId = curse_id

    // FabricApi
    modrinthDependency("P7dR8mSH", uploadConfig.REQUIRED)
    curseDependency("fabric-api", uploadConfig.REQUIRED)
    // Fabric Language Kotlin
    modrinthDependency("Ha28R6CL", uploadConfig.REQUIRED)
    curseDependency("fabric-language-kotlin", uploadConfig.REQUIRED)
}*/
