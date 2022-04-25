plugins {
    kotlin("jvm") version "1.6.10" apply false
    id("net.kyori.blossom") version "1.3.0" apply false
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("gg.essential.multi-version.root")
}

preprocess {
    "1.8.9-forge"(10809, "srg") {}
}