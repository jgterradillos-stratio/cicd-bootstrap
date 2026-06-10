@NonCPS
def loadBootstrap() {
    def content = Jenkins.instance
        .getExtensionList('org.jenkinsci.plugins.configfiles.GlobalConfigFiles')[0]
        .getById('libpipeline-bootstrap')
        .content
    new groovy.json.JsonSlurperClassic().parseText(content)
}

def call(Closure body = {}) {
    def bootstrap = loadBootstrap()
    echo "[hose] Bootstrap config loaded: ${bootstrap}"

    def folder = env.JOB_NAME.split('/')[0].toLowerCase()
    echo "[hose] Detected folder: ${folder}"

    def libraryRef = bootstrap.find { lib, folders -> folders.contains(folder) }?.key
    echo "[hose] Resolved library: ${libraryRef}"

    if (!libraryRef) {
        libraryRef = 'libpipeline'
    }

    library libraryRef
    hoseExecutor(body)
}
