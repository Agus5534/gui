plugins {
    id("gui.publishing-conventions")
    id("io.papermc.paperweight.userdev") version "1.7.1"
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
    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
}