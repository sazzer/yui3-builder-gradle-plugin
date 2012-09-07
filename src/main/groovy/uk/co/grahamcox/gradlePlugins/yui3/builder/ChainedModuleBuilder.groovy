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
 * Chained module builder that delegates to a chain of other module builders
 */
class ChainedModuleBuilder implements ModuleBuilder {
    /** The chain of module builders to use */
    def moduleBuilders

    /**
     * Actually build the module into the output base directory
     * @param module The module to write to the output
     * @param outputBase The base directory to write modules to
     */
    @Override
    void build(Module module, File outputBase) {
        moduleBuilders.each {ModuleBuilder builder -> builder.build(module, outputBase) }
    }
}
