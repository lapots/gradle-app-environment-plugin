package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.DownloadCore
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
open class DownloadTask : DefaultTask() {

    @TaskAction
    fun download() {
        val extension = this.project.extensions.getByName("app") as ApplicationEnvironmentExtension

        val identifier = this.project.properties["installationId"]
        if (null == identifier) {
            DownloadCore(extension).execute()
        } else {
            DownloadCore(extension)
        }
    }
}