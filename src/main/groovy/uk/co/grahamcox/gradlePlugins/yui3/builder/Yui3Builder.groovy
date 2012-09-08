/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package uk.co.grahamcox.gradlePlugins.yui3.builder

/**
 * The actual class that does all the work of building a set of YUI3 modules from a source directory
 */
class Yui3Builder {

    /** The module finder to use */
    private ModuleFinder moduleFinder

    /** The module builder to use */
    private ModuleBuilder moduleBuilder

    /**
     * Construct the builder, setting up everything necessary to actually build the modules
     */
    public Yui3Builder() {
        moduleFinder = new ModuleFinder()
        moduleFinder.moduleLoader = new ModuleLoader()

        def debugModuleFileNamer = new TemplatedModuleFileNamer()
        debugModuleFileNamer.template = '${name}/${name}-debug.js'
        def debugModuleWriter = new DebugModuleWriter()
        def debugModuleBuilder = new SimpleModuleBuilder()
        debugModuleBuilder.moduleFileNamer = debugModuleFileNamer
        debugModuleBuilder.moduleWriter = debugModuleWriter

        def rawModuleFileNamer = new TemplatedModuleFileNamer()
        rawModuleFileNamer.template = '${name}/${name}.js'
        def rawModuleBuilder = new SimpleModuleBuilder()
        rawModuleBuilder.moduleFileNamer = rawModuleFileNamer
        rawModuleBuilder.moduleWriter = debugModuleWriter

        def minModuleFileNamer = new TemplatedModuleFileNamer()
        minModuleFileNamer.template = '${name}/${name}-min.js'
        def minModuleBuilder = new SimpleModuleBuilder()
        minModuleBuilder.moduleFileNamer = minModuleFileNamer
        minModuleBuilder.moduleWriter = debugModuleWriter

        moduleBuilder = new ChainedModuleBuilder()
        moduleBuilder.moduleBuilders = [rawModuleBuilder, debugModuleBuilder, minModuleBuilder]
    }

    /**
     * Find all of the modules in the Input Directory, and build them into the Output Directory
     * @param inputDirectory the Input directory to scan
     * @param outputDirectory the output directory to write modules into
     */
    def build(File inputDirectory, File outputDirectory) {
        moduleFinder.findModules(inputDirectory).each {module ->
            moduleBuilder.build(module, outputDirectory)
        }
    }
}
