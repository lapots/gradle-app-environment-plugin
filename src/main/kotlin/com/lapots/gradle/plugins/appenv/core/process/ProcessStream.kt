package com.lapots.gradle.plugins.appenv.core.process

import com.lapots.gradle.plugins.appenv.core.PluginConstants.MSI_RUNNER
import mu.KLogging
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.ExecuteWatchdog
import org.apache.commons.io.FilenameUtils

/**
 * DSL for fancy installing via cmd.
 *
 * Currently support only MSI.
 */
class ProcessStream {
    companion object : KLogging()

    var executable = "" // /downloads/file.msi
    var destination = "" // /installations

    infix fun do_install(closure: ProcessStream.() -> Unit) {
        install()
    }

    fun from(closure: () -> String) : ProcessStream {
        executable = closure()
        return this
    }

    fun to(closure: () -> String) : ProcessStream {
        destination = closure()
        return this
    }

    private fun install() {
        if (destination.isEmpty()) { destination = FilenameUtils.getPath(executable) }
        logger.info { "Attempt to install $executable into $destination" }

        val cmdLine = CommandLine(MSI_RUNNER)
        cmdLine.addArgument("/q")
        cmdLine.addArgument("/i")
        cmdLine.addArgument(executable)
        cmdLine.addArgument("INSTALLOCATION=\"$destination\"")
        cmdLine.addArgument("ADDLOCAL=\"all\"")

        val executor = DefaultExecutor()
        executor.setExitValue(1)

        executor.watchdog = ExecuteWatchdog(60000)
        val exitValue = executor.execute(cmdLine)

        logger.info { "Application finished with $exitValue" }
    }
}