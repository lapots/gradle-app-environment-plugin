package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ApplicationEnvironmentGroovyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.extensions.create("env", ApplicationEnvironmentContainerGroovyExtension.class)
        project.task([ "type" : DownloadGroovyTask.class ], "download")
        project.task([ "type" : InstallGroovyTask.class ], "install")
        project.task([ "type" : PrintingGroovyTask.class ], "print")
    }
}
