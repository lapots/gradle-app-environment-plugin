package com.lapots.gradle.plugins.appenv

import org.gradle.api.*

/**
 * Main plugin class.
 */
class ApplicationEnvironmentPlugin : Plugin<Project> {

    fun <Y> Project.createExtension(
            var1: String,
            clazz: Class<Y>,
            vararg params: Any
    ) {
        this.extensions.create(var1, clazz, params)
    }

    override fun apply(project: Project) {
        project.createExtension(
                    "app",
                    ApplicationEnvironmentExtension::class.java,
                    project
                )
        project.task(mapOf ("type" to Download::class.java), "downloadEnv")
    }
}
