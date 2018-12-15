tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)

        gradle.includedBuilds.forEach {
            dependsOn(it.task(":clean"))
        }
    }

    val build by registering {
        gradle.includedBuilds.forEach {
            dependsOn(it.task(":build"))
        }
    }
}
