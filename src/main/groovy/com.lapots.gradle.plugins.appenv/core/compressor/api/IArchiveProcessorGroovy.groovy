package com.lapots.gradle.plugins.appenv.core.compressor.api

interface IArchiveProcessorGroovy {
    def process(inputStream, destination)
}