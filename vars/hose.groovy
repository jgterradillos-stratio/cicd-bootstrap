def call(Closure body = {}) {
    def folder = env.JOB_NAME.split('/')[0].toLowerCase()
    if (folder == 'egeo') {
        library "libpipelines@test/dynamic-library"
    } else {
        library "libpipelines"
    }
    executor(body)
}
