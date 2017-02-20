package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import com.lapots.gradle.plugins.appenv.core.PluginConstantsGroovy

class TaskSupportUtil {

    def doOneOrAll(project, executor) {
        def extension =
                project.extensions.getByName("env") as ApplicationEnvironmentContainerGroovyExtension

        def envId = project.properties[PluginConstantsGroovy.EXT_ID_PARAM]
        if (envId) {
            System.out.println("Project parameter found!")
            def env = extension.environs[envId]
            if (env) {
                executor.extension = env
                executor.execute()
            } else {
                throw new IllegalStateException("No environment with id [$envId] found!")
            }
        } else {
            System.out.println("No project parameter [${ PluginConstantsGroovy.EXT_ID_PARAM }] found!")
            extension.environs.each {
                executor.extension = it.value
                executor.execute()
            }
        }
    }

}
