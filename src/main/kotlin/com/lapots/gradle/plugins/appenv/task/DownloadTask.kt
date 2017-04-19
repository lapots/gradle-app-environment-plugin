package com.lapots.gradle.plugins.appenv.task

import com.lapots.gradle.plugins.appenv.TaskSupport
import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentExtension
import com.lapots.gradle.plugins.appenv.core.DownloadCore
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Downloads file.
 */
open class DownloadTask : DefaultTask() {

    @TaskAction
    fun download() {
        TaskSupport.executeAction(project, DownloadCore(ApplicationEnvironmentExtension()))
    }
}