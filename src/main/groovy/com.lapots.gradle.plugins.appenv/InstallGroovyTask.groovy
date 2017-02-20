package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.FileProcessingCoreGroovy
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class InstallGroovyTask extends DefaultTask {

    @TaskAction
    def install() {
        TaskSupportUtil.doOneOrAll(project, new FileProcessingCoreGroovy())
    }

}
