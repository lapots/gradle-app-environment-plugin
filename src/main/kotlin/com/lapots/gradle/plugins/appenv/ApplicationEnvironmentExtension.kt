package com.lapots.gradle.plugins.appenv

import groovy.lang.Closure
import org.gradle.api.Project
import org.gradle.util.Configurable
import org.gradle.util.ConfigureUtil

/**
 * Extension for {@link ApplicationEnvironmentPlugin}.
 */
open class ApplicationEnvironmentExtension : Configurable<ApplicationEnvironmentExtension> {

    var srcLink: String
    var downloadPath: String
    var installationPath: String
    var startCommand: String
    var stopCommand: String
    var project: Any?

    constructor(project: Object) {
        srcLink = ""
        downloadPath = ""
        installationPath = ""
        startCommand = ""
        stopCommand = ""
        this.project = project
    }

    constructor(srcLink: String,
                downloadPath: String,
                installationPath: String,
                startCommand: String = "",
                stopCommand: String = "",
                project: Project) {
        this.srcLink = srcLink
        this.downloadPath = downloadPath
        this.installationPath = installationPath
        this.startCommand = startCommand
        this.stopCommand = stopCommand
        this.project = project
    }

    override fun configure(p0: Closure<*>?): ApplicationEnvironmentExtension {
        val downloadAction = ConfigureUtil.configure(p0, DownloadAction(project as Project))
        downloadAction.execute()
        return this
    }
}
