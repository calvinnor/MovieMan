package com.calvinnor.core.dependencies.base

interface BaseModule {

    /**
     * Loads the module definitions into the DI's registry
     */
    fun load()

    /**
     * Unloads the module definitions from the DI's registry
     */
    fun unload()

}
