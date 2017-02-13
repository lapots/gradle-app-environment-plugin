package com.lapots.gradle.plugins.appenv.core.compressor

import com.lapots.gradle.plugins.appenv.core.PluginUtilsGroovy
import com.lapots.gradle.plugins.appenv.core.compressor.core.TarArchiveProcessorGroovy
import com.lapots.gradle.plugins.appenv.core.compressor.core.XZArchiveProcessorGroovy
import org.apache.commons.io.FilenameUtils

class UnpackStreamGroovy {
    def archive
    def destination

    def doUnpack(closure) {
        resolveAgainst(closure, this)
        processResource()
    }

    def from(closure) {
        archive = closure()
        this
    }

    def to(closure) {
        destination = closure()
        this
    }

    def processResource() {
        if (!destination) { destination = FilenameUtils.getPath(archive) }

        new FileInputStream(archive).with {
            new BufferedInputStream(it).with {
                def compressorInput = CompressorContainer.provideCompressorFor(archive)
                if (!compressorInput) {
                    def destFile = PluginUtilsGroovy.buildPath(destination, FilenameUtils.getBaseName(archive))
                    compressorInput.process(it, destFile)
                } else {
                    throw new IllegalStateException("Not found de-compressor for extension!")
                }
            }
        }
    }

    def resolveAgainst(closure, object) {
        closure.delegate = object
        closure.setResolveStrategy Closure.DELEGATE_ONLY
        closure()
    }

    static class CompressorContainer {
        static def unpackMap = [
                "xz" : new XZArchiveProcessorGroovy(),
                "tar" : new TarArchiveProcessorGroovy()
        ]

        static def provideCompressorFor(filename) {
            unpackMap[FilenameUtils.getExtension(filename)]
        }
    }
}
