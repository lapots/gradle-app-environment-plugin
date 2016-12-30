package com.lapots.gradle.plugins.appenv

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
class Download : DefaultTask {

    val downloadAction: DownloadAction

    constructor() {
        downloadAction = DownloadAction(project)
    }

    @TaskAction
    fun download() {
        downloadAction.execute()
    }
}