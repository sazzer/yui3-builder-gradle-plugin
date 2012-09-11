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
 * Implementation of the ModuleWriter that uses a delegate to write the raw module and then
 * transforms it on the way out
 */
abstract class TransformingModuleWriter implements ModuleWriter {
    /** The module writer to delegate to */
    private ModuleWriter moduleWriter

    /**
     * Set the module writer to use
     * @param moduleWriter the module writer to use
     */
    void setModuleWriter(ModuleWriter moduleWriter) {
        this.moduleWriter = moduleWriter
    }
/**
     * Write the provided module
     * @param module The module to write
     * @return the contents of the module file
     * @throws IOException if the module couldn't be written
     */
    @Override
    String write(Module module) {
        return transform(moduleWriter.write(module))
    }

    /**
     * Transform the written module
     * @param input the untransformed module
     * @return the transformed module
     */
    protected abstract String transform(String input)
}
