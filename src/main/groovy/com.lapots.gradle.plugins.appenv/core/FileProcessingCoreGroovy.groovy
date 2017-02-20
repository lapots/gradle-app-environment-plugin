package com.lapots.gradle.plugins.appenv.core

import com.lapots.gradle.plugins.appenv.core.compressor.UnpackStreamGroovy
import com.lapots.gradle.plugins.appenv.core.process.ProcessStreamGroovy
import org.apache.commons.io.FilenameUtils

class FileProcessingCoreGroovy implements IExecutorGroovy {
    def extension

    @Override
    def execute() {
        processFile(FilenameUtils.getName(extension.srcLink))
    }

    def processFile(file) {
        switch (FilenameUtils.getExtension(file)) {
            case file in PluginConstantsGroovy.SUPPORTED_ARCHIVE_EXTENSIONS:
                processArchive(file)
                break
            case "msi":
                processMsi(file)
                break
            default:
                // logger
                break
        }
    }

    def processArchive(archive) {
        def file = PluginUtilsGroovy.buildPath(extension.downloadPth, archive)
        new UnpackStreamGroovy().doUnpack {
            from { file }
            to { extension.installPath }
        }

        processFile(FilenameUtils.getBaseName(file))
    }

    def processMsi(msi) {
        def file = PluginUtilsGroovy.buildPath(extension.downloadPath, msi)
        new ProcessStreamGroovy().doInstall {
            from { file }
            to { extension.installPath }
        }
    }
}
