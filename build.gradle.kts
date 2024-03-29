plugins {
    id("fabric-loom") version "1.4-SNAPSHOT"
    id("maven-publish")
    id("com.diffplug.spotless") version "6.22.0"
    java
}

val mod_version = System.getenv("MOD_VERSION") ?: "0.0.0"
val maven_group: String by project
val minecraft_version: String by project
version = mod_version
group = maven_group

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    maven { url = uri("https://maven.shedaniel.me/") }
    maven { url = uri("https://maven.terraformersmc.com/releases/") }
}

val yarn_mappings: String by project
val loader_version: String by project
val fabric_version: String by project
dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings("net.fabricmc:yarn:$yarn_mappings:v2")
    modImplementation("net.fabricmc:fabric-loader:$loader_version")

    // Fabric API. This is technically optional, but you probably want it anyway.
    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")

    // Modrinth maven -> modImplementation("maven.modrinth:<modid>:<version-number>")
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core
    include(implementation("com.fasterxml.jackson.core", "jackson-core", "2.15.3"))
    include(implementation("com.fasterxml.jackson.core", "jackson-databind", "2.15.3"))
    // https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations
    include(implementation("com.fasterxml.jackson.core", "jackson-annotations", "2.15.3"))

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    modApi("me.shedaniel.cloth:cloth-config-fabric:12.0.109")
    modApi("com.terraformersmc:modmenu:8.0.0")
}

tasks.processResources {
//    inputs.property "version", project.version
//    inputs.property "minecraft_version", project.minecraft_version
//    inputs.property "loader_version", project.loader_version
    filteringCharset = "UTF-8"

    filesMatching("fabric.mod.json") {
        expand("version" to project.version,
            "minecraft_version" to minecraft_version,
            "loader_version" to loader_version)
    }
}

val targetJavaVersion = 17
tasks.withType<JavaCompile>().configureEach {
    // ensure that the encoding is set to UTF-8, no matter what the system default is
    // this fixes some edge cases with special characters not displaying correctly
    // see http://yodaconditions.net/blog/fix-for-java-file-encoding-problems-with-gradle.html
    // If Javadoc is generated, this must be specified in that task too.
    options.encoding = "UTF-8"
    if (targetJavaVersion >= 10 || JavaVersion.current().isJava10Compatible()) {
        options.release.set(targetJavaVersion)
    }
}

val archives_base_name: String by project
java {
    val javaVersion = JavaVersion.toVersion(targetJavaVersion)
    if (JavaVersion.current() < javaVersion) {
        toolchain.languageVersion.set(JavaLanguageVersion.of(targetJavaVersion))
    }
    base.archivesName.set(archives_base_name)
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${archives_base_name}"}
    }
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    java {
        palantirJavaFormat()
        target("src/main/java/**/*.java", "src/main/test/**/*.java")
        removeUnusedImports()
        importOrder("")
        trimTrailingWhitespace()
        indentWithSpaces(4)
    }
}

// configure the maven publication
//publishing {
//    publications {
//        mavenJava(MavenPublication) {
//            from components.java
//        }
//    }
//
//    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
//    repositories {
//        // Add repositories to publish to here.
//        // Notice: This block does NOT have the same function as the block in the top level.
//        // The repositories here will be used for publishing your artifact, not for
//        // retrieving dependencies.
//    }
//}
