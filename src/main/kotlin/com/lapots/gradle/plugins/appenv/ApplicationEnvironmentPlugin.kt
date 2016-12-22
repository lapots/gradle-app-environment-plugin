package com.lapots.gradle.plugins.appenv

import org.gradle.api.*

/**
 * Main plugin class.
 */
class ApplicationEnvironmentPlugin : Plugin<Project> {

    val PLUGIN_EXTENSION_NAME = "app"
    val TASK_GROUP_NAME = "App Env"

    fun <Y> Project.createExtension(
            var1: String,
            clazz: Class<Y>,
            vararg params: String
    ) {
        this.extensions.create(var1, clazz, params)
    }

    override fun apply(project: Project) {
        project.extensions
                .create(
                    "app",
                    ApplicationEnvironmentExtensionWrapper::class.java,
                    project
                )

        addDownloadTask(project)
    }

    private fun addDownloadTask(project: Project) {
        project.task(
                mapOf(
                    "group" to TASK_GROUP_NAME,
                    "description" to "Downloads required environment"
                ),
                "downloadEnvironment"
        ).doFirst {
            // ???
            val pluginExtension = project.extensions
                    .getByName(
                            PLUGIN_EXTENSION_NAME
                    ) as ApplicationEnvironmentExtensionWrapper
        }
    }
}
