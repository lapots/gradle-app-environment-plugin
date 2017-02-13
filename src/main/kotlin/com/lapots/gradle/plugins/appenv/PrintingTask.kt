package com.lapots.gradle.plugins.appenv

import com.lapots.gradle.plugins.appenv.core.ApplicationEnvironmentContainerExtension
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

/**
 * Task for printing environs.
 */
@Deprecated("Moved to Groovy")
open class PrintingTask :  DefaultTask() {

    @TaskAction
    fun print() {
        val extension = this.project.extensions.getByName("env")  as ApplicationEnvironmentContainerExtension

        extension.environs.forEach {
            println(it.value)
        }
    }
}
