plugins {
    id("gui.publishing-conventions")
    id("io.papermc.paperweight.userdev") version "1.6.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}

dependencies {
    api(project(":gui-menu-api"))
    paperDevBundle("1.20.5-R0.1-SNAPSHOT")
}