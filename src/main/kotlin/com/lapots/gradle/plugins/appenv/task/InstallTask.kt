package com.lapots.gradle.plugins.appenv.task

import com.lapots.gradle.plugins.appenv.TaskSupport
import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentExtension
import com.lapots.gradle.plugins.appenv.core.FileProcessingCore
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Installs / Unpacks file.
 */
open class InstallTask : DefaultTask() {

    @TaskAction
    fun install() {
        // hm hm
        TaskSupport.executeAction(project, FileProcessingCore(ApplicationEnvironmentExtension()))
    }
}