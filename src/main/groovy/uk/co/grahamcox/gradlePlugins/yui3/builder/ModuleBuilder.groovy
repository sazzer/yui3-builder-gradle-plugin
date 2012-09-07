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
 * Actual builder to build a Module into an output file
 */
class ModuleBuilder {
    /** The module writer to use */
    def moduleWriter;

    /** The module file namer to use */
    def moduleFileNamer;

    /**
     * Actually build the module into the output base directory
     * @param module The module to write to the output
     * @param outputBase The base directory to write modules to
     */
    public void build(Module module, File outputBase) {
        String moduleContents = moduleWriter.write(module)
        File targetFile = moduleFileNamer.writeFileName(outputBase, module)
        targetFile.parentFile.mkdirs()
        targetFile.write(moduleContents)
    }
}

