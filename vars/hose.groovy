def call(Closure body = {}) {
    def libraryRef = hoseResolveLibrary()
    echo "[hose] Folder: ${env.JOB_NAME.split('/')[0]} → Library: ${libraryRef}"
    library libraryRef
    hoseExecutor(body)
}
