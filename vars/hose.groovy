def call(Closure body = {}) {

    // Plugin: ConfigFileProvider  Method: configFileProvider
    // Plugin: ConfigFileProvider  Method: configFile
    def bootstrap
    node {
        configFileProvider([configFile(fileId: 'libpipeline-bootstrap', variable: 'jsonFile')]) {
            bootstrap = readJSON file: jsonFile
        }
    }

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
