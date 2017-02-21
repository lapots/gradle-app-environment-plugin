package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerExtension
import com.lapots.gradle.plugins.appenv.core.AbstractExecutor
import com.lapots.gradle.plugins.appenv.core.PluginConstants
import com.lapots.gradle.plugins.appenv.core.PluginConstants.EXTENSION_NAME
import mu.KLogging
import org.gradle.api.Project

object TaskSupport : KLogging() {

    fun executeAction(project: Project, executor: AbstractExecutor) {
        val extension =
                project.extensions.getByName(EXTENSION_NAME) as ApplicationEnvironmentContainerExtension
        val envId = project.properties[PluginConstants.EXT_ID_PARAM]
        if (null != envId) {
            logger.info { "Environment parameter $envId found!" }
            val env = extension.environs[envId]
            if (null != env) {
                executor.extension = env
                executor.execute()
            } else {
                throw IllegalStateException("No environment with id [$envId] found!")
            }
        } else {
            logger.info { "No project parameter ${ PluginConstants.EXT_ID_PARAM } found!" }
            for ((id, environment) in extension.environs) {
                executor.extension = environment
                executor.execute()
            }
        }
    }

}