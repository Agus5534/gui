plugins {
    id("gui.publishing-conventions")
    id("io.papermc.paperweight.userdev") version "1.5.10"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks {
    assemble {
        dependsOn(reobfJar)
    }
}

dependencies {
    api(project(":gui-menu-api"))
    paperDevBundle("1.20-R0.1-SNAPSHOT")
}