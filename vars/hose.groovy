def call(Closure body = {}) {
    library "libpipelines@test/dynamic-library"
    hose(body)
}
