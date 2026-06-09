def call(Closure body = {}) {
    def version = env.PIPELINE_LIBRARY_VERSION ?: 'master'
    library "libpipelines@${version}"
    env.BOOTSTRAP_LOADED = 'true'
    hose(body)
}
