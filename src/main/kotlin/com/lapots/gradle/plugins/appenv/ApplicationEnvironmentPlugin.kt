package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerExtension
import com.lapots.gradle.plugins.appenv.task.DownloadTask
import com.lapots.gradle.plugins.appenv.task.InstallTask
import mu.KLogging
import org.gradle.api.*

/**
 * Main plugin class.
 */
class ApplicationEnvironmentPlugin : Plugin<Project> {

    companion object : KLogging()

    override fun apply(project: Project) {
        logger.info { "Initializing plugin" }

        project.extensions.create("env", ApplicationEnvironmentContainerExtension::class.java)
        project.task(mapOf ("type" to DownloadTask::class.java), "download")
        project.task(mapOf ("type" to InstallTask::class.java), "install")

        project.task(mapOf ("type" to PrintingTask::class.java), "print")
    }
}
