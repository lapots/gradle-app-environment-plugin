# gradle-app-environment-plugin
Plugin for gradle that allows to download and deploy external applications

# example
The prototype for it looks like this


        env {

            app("env-id") {
                srcLink { "" }
                downloadPath { "" }
                installPath { "" }
            }


        }


Sequenced run of [download] and [install] allows to download .xz archive into [downloadPath], unpack .xz and unpack of .tar
with its content to the corresponding [installPath]
