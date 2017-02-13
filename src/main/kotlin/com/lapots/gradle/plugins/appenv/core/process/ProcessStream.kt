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
@Deprecated("Moved to Groovy")
class ProcessStream {
    companion object : KLogging()

    var executable = "" // /downloads/file.msi
    var destination = "" // /installations

    infix fun do_install(closure: ProcessStream.() -> Unit) {
        closure()
        install()
    }

    infix fun do_uninstall(closure: ProcessStream.() -> Unit) {
        uninstall()
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
        cmdLine.addArgument("/a")
        cmdLine.addArgument(executable)
        cmdLine.addArgument("/qn")
        cmdLine.addArgument("INSTALLLOCATION=$destination")
        cmdLine.addArgument("ADDLOCAL=all")

        val executor = DefaultExecutor()
        executor.setExitValue(0)

        executor.watchdog = ExecuteWatchdog(60000)
        val exitValue = executor.execute(cmdLine)

        logger.info { "Application finished with $exitValue" }
    }

    // not sure if it is needed as it allows to remove folder directly
    private fun uninstall() {
        logger.info { "Attempt to uninstall $executable from $destination" }

        val cmdLine = CommandLine(MSI_RUNNER)
        cmdLine.addArgument("/x")
        cmdLine.addArgument(executable)

        val executor = DefaultExecutor()
        executor.setExitValue(1)

        executor.watchdog = ExecuteWatchdog(60000)
        val exitValue = executor.execute(cmdLine)

        logger.info { "Application finished with $exitValue" }
    }
}