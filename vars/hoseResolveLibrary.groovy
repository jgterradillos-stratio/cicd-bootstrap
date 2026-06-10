@NonCPS
def loadBootstrapFile() {
    def extensions = Jenkins.instance.getExtensionList('org.jenkinsci.plugins.configfiles.GlobalConfigFiles')
    def configFile = extensions[0].getById('libpipeline-bootstrap')    
    def content = configFile.content?.trim()
    if (!content) {
        throw new IllegalStateException("[loadBootstrapFile] Config file 'libpipeline-bootstrap' is empty")
    }

    try {
        return new groovy.json.JsonSlurperClassic().parseText(content)
    } catch (e) {
        throw new IllegalStateException("[loadBootstrapFile] Invalid JSON in 'libpipeline-bootstrap': ${e.message}")
    }
}

def call() {
    def bootstrap = loadBootstrapFile()
    def folder = env.JOB_NAME.split('/')[0]
    def libraryRef = bootstrap.find { lib, folders -> folders.contains(folder) }?.key
    return libraryRef ?: 'libpipelines'
}
