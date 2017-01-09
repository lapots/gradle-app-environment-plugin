# gradle-app-environment-plugin
Plugin for gradle that allows to download and deploy external applications

# example
The prototype for it looks like this

```
    app {
       
        environment {
            srcLink ''
            downloadPath ''
            installationPath ''
            startCommand '' // optional parameter for applications that cannot be start via .exe
            stopCommand '' // optional parameter
        }
       
        environment {
            srcLink ''
            downloadPath ''
            installationPath ''
        }
    
    }
```

# current state

```
app {
    srcLink "http://downloads.haskell.org/~ghc/8.0.1/ghc-8.0.1-x86_64-unknown-mingw32.tar.xz"
    downloadPath project(':').projectDir.absolutePath + "\\applications\\plugin"
    installPath project(':').projectDir.absolutePath + "\\applications\\plugin"
}
```

Sequenced run of [download] and [install] allows to download .xz archive into [downloadPath], unpack .xz and unpack of .tar
with its content to the corresponding [installPath]
