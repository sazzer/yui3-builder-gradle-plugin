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

import groovy.io.FileType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Means to actually find all of the module definitions
 */
class ModuleFinder {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(ModuleFinder)
    /** The name of the build.properties file to look for */
    def buildFile = "build.properties"

    /** The module loader to use */
    def moduleLoader

    /**
     * Scan the source directory for all of the modules and load them up
     * @param source the source directory to scan
     */
    def findModules(File source) {
        def modules = []
        if (source.isDirectory()) {
            source.eachFileRecurse(FileType.FILES, { f ->
                if (f.name == buildFile) {
                    LOG.debug("Found new module file: {}", f)
                    def module = moduleLoader.load(f)
                    if (module != null) {
                        modules.add(module)
                    }
                }
            })
        }
        return modules
    }
}
