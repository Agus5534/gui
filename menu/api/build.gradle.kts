plugins {
    id("gui.publishing-conventions")
}

dependencies {
    api(libs.annotations)

    arrayOf("validation", "bukkit").forEach {
        api("team.unnamed:commons-$it:3.1.0")
    }

    compileOnly("io.papermc.paper:paper-api:1.17-R0.1-SNAPSHOT")
}

configure<JavaPluginExtension> {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}