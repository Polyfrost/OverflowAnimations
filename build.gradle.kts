import gg.essential.gradle.util.noServerRunConfigs

plugins {
    id("gg.essential.multi-version")
    id("gg.essential.defaults.repo")
    id("gg.essential.defaults.java")
    id("gg.essential.defaults.loom")
    id("com.github.johnrengelman.shadow")
    id("net.kyori.blossom")
    java
}

val mod_name: String by project
val mod_version: String by project
val mod_id: String by project

preprocess {
    vars.put("MODERN", if (project.platform.mcMinor >= 16) 1 else 0)
}

blossom {
    replaceToken("@VER@", mod_version)
    replaceToken("@NAME@", mod_name)
    replaceToken("@ID@", mod_id)
}

version = mod_version
group = "cc.woverflow"
base {
    archivesName.set(mod_name)
}

loom.noServerRunConfigs()
loom {
    if (project.platform.isLegacyForge) {
        launchConfigs.named("client") {
            arg("--tweakClass", "cc.woverflow.onecore.tweaker.OneCoreTweaker")
            property("onecore.mixin", "mixins.${mod_id}.json")
            property("mixin.debug.export", "true")
        }
    }
    if (project.platform.isForge) {
        forge {
            mixinConfig("mixins.${mod_id}.json")
        }
    }
    mixin.defaultRefmapName.set("mixins.${mod_id}.refmap.json")
}

repositories {
    maven("https://repo.woverflow.cc/")
}

val shade: Configuration by configurations.creating {
    configurations.implementation.get().extendsFrom(this)
}

dependencies {
    if (platform.isLegacyForge) {
        compileOnly ("gg.essential:essential-$platform:2795") {
            exclude(module = "keventbus")
        }

        modCompileOnly(rootProject.files("libs/oam.jar"))
        shade("cc.woverflow:onecore-tweaker:1.3.0")
    }
    val onecore = "cc.woverflow:onecore:1.3.4"
    if (platform.isLegacyForge) {
        compileOnly(onecore)
    } else {
        modImplementation(onecore)
    }
    compileOnly ("org.spongepowered:mixin:0.8.5-SNAPSHOT")
}

tasks.processResources {
    inputs.property("id", mod_id)
    inputs.property("name", mod_name)
    val java = if (project.platform.mcMinor >= 18) {
        17
    } else {if (project.platform.mcMinor == 17) 16 else 8 }
    val compatLevel = "JAVA_${java}"
    inputs.property("java", java)
    inputs.property("java_level", compatLevel)
    inputs.property("version", mod_version)
    inputs.property("mcVersionStr", project.platform.mcVersionStr)
    filesMatching(listOf("mcmod.info", "mixins.${mod_id}.json", "mods.toml")) {
        expand(mapOf(
            "id" to mod_id,
            "name" to mod_name,
            "java" to java,
            "java_level" to compatLevel,
            "version" to mod_version,
            "mcVersionStr" to project.platform.mcVersionStr
        ))
    }
    filesMatching("fabric.mod.json") {
        expand(mapOf(
            "id" to mod_id,
            "name" to mod_name,
            "java" to java,
            "java_level" to compatLevel,
            "version" to mod_version,
            "mcVersionStr" to project.platform.mcVersionStr.substringBeforeLast(".") + ".x"
        ))
    }
}

tasks {
    withType(Jar::class.java) {
        if (project.platform.isFabric) {
            exclude("mcmod.info", "mods.toml")
        } else {
            exclude("fabric.mod.json")
            if (project.platform.isLegacyForge) {
                exclude("mods.toml")
            } else {
                exclude("mcmod.info")
            }
        }
    }
    named<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar>("shadowJar") {
        archiveClassifier.set("dev")
        configurations = listOf(shade)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    remapJar {
        input.set(shadowJar.get().archiveFile)
        archiveClassifier.set("")
    }
    jar {
        manifest {
            attributes(mapOf(
                "ModSide" to "CLIENT",
                "TweakOrder" to "0",
                "TweakClass" to "cc.woverflow.onecore.tweaker.OneCoreTweaker",
                "ForceLoadAsMod" to true
            ))
        }
        dependsOn(shadowJar)
        archiveClassifier.set("")
        enabled = false
    }
}