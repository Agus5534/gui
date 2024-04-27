rootProject.name = "gui"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://papermc.io/repo/repository/maven-public/")

    }
}

// item modules
arrayOf("api", "skull-api").forEach {
    includePrefixed("item:$it")
}

// menu modules
arrayOf("api", "plugin").forEach {
    includePrefixed("menu:$it")
}

// menu adapters
arrayOf("1_17", "1_18", "1_18_2", "1_19", "1_19_2", "1_19_3", "1_20", "1_20_2", "1_20_3").forEach {
    includePrefixed("menu:adapt:v$it")
}

fun includePrefixed(name: String) {
    val kebabName = name.replace(':', '-')
    val path = name.replace(':', '/')
    val baseName = "${rootProject.name}-$kebabName"

    include(baseName)
    project(":$baseName").projectDir = file(path)
}