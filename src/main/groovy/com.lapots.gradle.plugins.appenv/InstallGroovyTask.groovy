package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import com.lapots.gradle.plugins.appenv.core.FileProcessingCoreGroovy
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class InstallGroovyTask extends DefaultTask {

    @TaskAction
    def install() {
        def extension =
                project.extensions.getByName("env") as ApplicationEnvironmentContainerGroovyExtension

        extension.environs.each {
            new FileProcessingCoreGroovy(extension : it.value).execute()
        }
    }

}
