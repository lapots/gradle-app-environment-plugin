package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.FileProcessingCore
import com.lapots.gradle.plugins.appenv.core.spec.InstallTaskSpec
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Installs / Unpacks file.
 */
open class InstallTask : DefaultTask(), InstallTaskSpec {
    var confExtension = ApplicationEnvironmentExtension()

    override fun file(name: String) {
        confExtension.srcLink = name
    }

    override fun from(downloadPath: String) {
        confExtension.downloadPath = downloadPath
    }

    override fun to(installationPath: String) {
        confExtension.installPath = installationPath
    }

    @TaskAction
    fun install() {
        var extension = this.project.extensions.getByName("app") as ApplicationEnvironmentExtension

        if (null == extension) { extension = confExtension }

        if (confExtension.srcLink.isBlank() || confExtension.downloadPath.isBlank()) {
            throw IllegalStateException("Install Task is not configured fully!")
        }

        FileProcessingCore(extension).execute()
    }
}