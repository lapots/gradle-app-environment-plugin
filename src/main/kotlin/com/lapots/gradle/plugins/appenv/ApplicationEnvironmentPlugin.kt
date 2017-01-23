package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainer
import mu.KLogging
import org.gradle.api.*

/**
 * Main plugin class.
 */
class ApplicationEnvironmentPlugin : Plugin<Project> {

    companion object : KLogging()

    override fun apply(project: Project) {
        logger.debug { "Initializing plugin" }

        project.extensions.create("env", ApplicationEnvironmentContainer::class.java)
        project.task(mapOf ("type" to DownloadTask::class.java), "download")
        project.task(mapOf ("type" to InstallTask::class.java), "install")
    }
}
