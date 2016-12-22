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
