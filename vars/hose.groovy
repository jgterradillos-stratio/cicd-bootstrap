def call(Closure body = {}) {

    if (!env.BOOTSTRAP_LOADED) {
            env.BOOTSTRAP_LOADED = 'true'
            library "libpipelines@test/dynamic-library"
            executor(body)
    }  
    
}
