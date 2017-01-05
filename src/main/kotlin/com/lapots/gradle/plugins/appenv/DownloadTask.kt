package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.DownloadCore
import com.lapots.gradle.plugins.appenv.core.spec.DownloadTaskSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
open class DownloadTask : DefaultTask(), DownloadTaskSpec {
    var confExtension = ApplicationEnvironmentExtension()

    override fun from(srcLink: String) {
        confExtension.srcLink = srcLink
    }

    override fun to(downloadPath: String) {
        confExtension.downloadPath = downloadPath
    }

    @TaskAction
    fun download() {
        var extension = this.project.extensions.getByName("app") as ApplicationEnvironmentExtension

        if (null == extension) { extension = confExtension }

        if (extension.downloadPath.isBlank() || extension.srcLink.isBlank()) {
            throw IllegalStateException("Download task is not configured fully!")
        }

        DownloadCore(extension).execute()
    }
}