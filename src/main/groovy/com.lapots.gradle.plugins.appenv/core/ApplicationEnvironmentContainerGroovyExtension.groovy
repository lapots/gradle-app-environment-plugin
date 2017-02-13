package com.lapots.gradle.plugins.appenv.core


class ApplicationEnvironmentContainerGroovyExtension {
    def environs = [:]

    def app(id, closure) {
        def extension = new ApplicationEnvironmentGroovyExtension()
        extension.installationId = id
        environs[id] = extension

        resolveAgainst(closure, extension)
    }

    class ApplicationEnvironmentGroovyExtension {
        def installationId
        def srcLink
        def downloadPath
        def installPath

        @Override
        public String toString() {
            return "ApplicationEnvironmentGroovyExtension{" +
                    "installationId=$installationId , " +
                    "srcLink=$srcLink , " +
                    "downloadPath=$downloadPath ," +
                    "installPath=$installPath ," +
                    '}';
        }
    }

    def resolveAgainst(closure, object) {
        closure.delegate = object
        closure.setResolveStrategy Closure.DELEGATE_ONLY
        closure()
    }
}
