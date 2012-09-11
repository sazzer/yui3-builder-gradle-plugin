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

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import groovy.io.FileType

/**
 * Means to load a Module definition from the provided files
 */
class ModuleLoader {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(ModuleLoader)
    /**
     * Build a Module definition from a build.properties file
     * @param buildFile the build properties
     */
    def load(File buildFile) {
        LOG.trace("Loading module from file: {}", buildFile)
        Properties properties = new Properties()
        FileReader fileReader = new FileReader(buildFile)
        try {
            properties.load(fileReader)
            return buildModule(properties, buildFile.parentFile)
        }
        finally {
            fileReader.close()
        }
    }

    /**
     * Build a Module definition from a loaded build.properties file
     * @param properties the properties for the module
     * @param baseDir the base directory of the module
     */
    private def buildModule(Properties properties, File baseDir) {
        Module module = new Module()
        if (!properties.getProperty("component", "").isEmpty()) {
            module.moduleName = properties.getProperty("component")
            LOG.debug("Module name: {}", module.moduleName)
        }
        else {
            module.moduleName = baseDir.name
            LOG.warn("No module name specified. Using directory name: {}", module.moduleName)
        }

        if (!properties.getProperty("component.requires", "").isEmpty()) {
            module.dependencies = properties.getProperty("component.requires").split(",").collect {s -> s.trim()}
            LOG.debug("Module dependencies: {}", module.dependencies)
        }
        else {
            module.dependencies = []
            LOG.debug("No module dependencies specified")
        }

        File sourceDir = new File(baseDir, "js")
        String sourceFiles = properties.getProperty("component.jsfiles", "")
        if (!sourceFiles.isEmpty()) {
            if (sourceDir.isDirectory()) {
                sourceFiles.split(",").each {f ->
                    def file = new File(sourceDir, f)
                    if (file.isFile()) {
                        module.moduleSources.add(file)
                    }
                    else {
                        LOG.warn("Source file doesn't exist: {}", file)
                        throw new FileNotFoundException("Source file doesn't exist: " + file)
                    }
                }
            }
            else {
                LOG.error("Source directory doesn't exist: {}", sourceDir)
                throw new FileNotFoundException("Source directory doesn't exist: " + sourceDir)
            }
        }
        else {
            if (sourceDir.isDirectory()) {
                LOG.warn("No source files specified for module: {}. Using all files in {}", module.moduleName, sourceDir)
                sourceDir.eachFileRecurse(FileType.FILES, { File f ->
                    if (f.name.toLowerCase().endsWith(".js")) {
                        module.moduleSources.add(f)
                    }
                })
            }
            else {
                LOG.debug("No sources and no source files. Empty module")
            }
        }
        return module
    }
}
