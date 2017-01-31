package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerExtension
import com.lapots.gradle.plugins.appenv.core.FileProcessingCore
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Installs / Unpacks file.
 */
open class InstallTask : DefaultTask() {

    @TaskAction
    fun install() {
        val extension = this.project.extensions.getByName("env")
                as ApplicationEnvironmentContainerExtension

        extension.environs.forEach {
            FileProcessingCore(it.value).execute()
        }
    }
}