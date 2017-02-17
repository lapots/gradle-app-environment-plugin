package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerExtension
import com.lapots.gradle.plugins.appenv.core.DownloadCore
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
open class DownloadTask : DefaultTask() {

    @TaskAction
    fun download() {
        val extension = this.project.extensions.getByName("env")
                as ApplicationEnvironmentContainerExtension

        extension.environs.forEach {
            DownloadCore(it.value).execute()
        }
    }
}