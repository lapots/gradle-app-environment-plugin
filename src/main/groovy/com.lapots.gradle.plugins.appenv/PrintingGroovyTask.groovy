package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class PrintingGroovyTask extends DefaultTask {

    @TaskAction
    def print() {
        def extension =
                project.extensions["env"] as ApplicationEnvironmentContainerGroovyExtension

        extension.environs.each {
            System.out.println(it.value)
        }
    }

}