package com.lapots.gradle.plugins.appenv

import org.gradle.api.*

/**
 * Main plugin class.
 */
class ApplicationEnvironmentPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.extensions.create(
                "app",
                ApplicationEnvironmentExtensionWrapper::class.java,
                project
        )
    }
}
