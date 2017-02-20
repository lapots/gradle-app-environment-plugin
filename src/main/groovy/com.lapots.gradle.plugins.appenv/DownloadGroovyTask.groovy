package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.DownloadCoreGroovy
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction


class DownloadGroovyTask extends DefaultTask {

    @TaskAction
    def download() {
        TaskSupportUtil.doOneOrAll(project, new DownloadCoreGroovy())
    }

}
