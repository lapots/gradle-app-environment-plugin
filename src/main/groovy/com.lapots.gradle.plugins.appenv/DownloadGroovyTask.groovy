package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import com.lapots.gradle.plugins.appenv.core.DownloadCoreGroovy
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class DownloadGroovyTask extends DefaultTask {

    @TaskAction
    def download() {
        def extension =
                project.extensions["env"] as ApplicationEnvironmentContainerGroovyExtension

        extension.environs.each {
            new DownloadCoreGroovy(extension : it.value).execute()
        }
    }

}
