package com.lapots.gradle.plugins.appenv.core.process

import com.lapots.gradle.plugins.appenv.core.PluginConstantsGroovy
import org.apache.commons.exec.CommandLine
import org.apache.commons.exec.DefaultExecutor
import org.apache.commons.exec.ExecuteWatchdog
import org.apache.commons.io.FilenameUtils

class ProcessStreamGroovy {

    def executable, destination

    def doInstall(closure) {
        resolveAgainst(closure, this)
        install()
    }

    def doUninstall(closure) {
        resolveAgainst(closure, this)
        uninstall()
    }

    def from(closure) {
        executable = closure()
        this
    }

    def to(closure) {
        destination = closure()
        this
    }

    def install() {
        if (!destination) {
            destination = FilenameUtils.getPath(executable)
        }

        def cmdLine = new CommandLine(PluginConstantsGroovy.MSI_RUNNER)
        cmdLine.addArgument("/a")
        cmdLine.addArgument(executable)
        cmdLine.addArgument("/qn")
        cmdLine.addArgument("INSTALLOCATION=$destination")
        cmdLine.addArgument("ADDLOCAL=all")

        def executor = new DefaultExecutor()
        executor.setExitValue(0)

        executor.watchdog = new ExecuteWatchdog(60000)
        def exitValue = executor.execute(cmdLine)

        println "Application finished with $exitValue"
    }

    def uninstall() {

        def cmdLine = new CommandLine(PluginConstantsGroovy.MSI_RUNNER)
        cmdLine.addArgument("/x")
        cmdLine.addArgument(executable)

        def executor = new DefaultExecutor()
        executor.setExitValue(1)

        executor.watchdog = new ExecuteWatchdog(60000)
        def exitValue = executor.execute(cmdLine)

        println "Application finished with $exitValue"
    }

    def resolveAgainst(closure, object) {
        closure.delegate = object
        closure.setResolveStrategy Closure.DELEGATE_ONLY
        closure()
    }
}
