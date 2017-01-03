package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.DownloadUtils
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
open class Download : DefaultTask() {

    @TaskAction
    fun download() {
        val extension = this.project.extensions.getByName("app") as ApplicationEnvironmentExtension
        DownloadUtils.download(extension.srcLink, extension.downloadPath)
    }
}