# gradle-app-environment-plugin
Plugin for gradle that allows to download and deploy external applications

# example
The prototype for it looks like this

        env {

            app("env-id") {
                srcLink = "http://storage.com/file.txt"
                downloadPath = "my_storage\\downloads"
                installPath = "my_storage\\installations"
            }

        }

Sequenced run of [download] and [install] allows to download .xz archive into [downloadPath], unpack .xz and unpack of .tar
with its content to the corresponding [installPath]

Also by providing parameter [id] for that task, you can specify the environment you'd like to download.

# future
I want to provide an ability to execute any possible environment via plugin. For example

    `gradle env env-id task`

Or maybe provide different version of a plugins, that extend the current but specificially designed to work with certain
environments.

