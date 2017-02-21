package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerGroovyExtension
import com.lapots.gradle.plugins.appenv.core.PluginConstantsGroovy
import groovy.util.logging.Slf4j

@Slf4j
class TaskSupportUtil {

    static def doOneOrAll(project, executor) {
        def extension =
                project.extensions.getByName("env") as ApplicationEnvironmentContainerGroovyExtension

        def envId = project.properties[PluginConstantsGroovy.EXT_ID_PARAM]
        if (envId) {
            log.info "Environment parameter $envId found!"
            def env = extension.environs[envId]
            if (env) {
                executor.extension = env
                executor.execute()
            } else {
                throw new IllegalStateException("No environment with id [$envId] found!")
            }
        } else {
            log.info "No project parameter ${ PluginConstantsGroovy.EXT_ID_PARAM } found!"
            extension.environs.each {
                executor.extension = it.value
                executor.execute()
            }
        }
    }

}
