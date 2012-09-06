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

import groovy.text.SimpleTemplateEngine

/**
 * Module File Namer that uses a simple template to write the filename
 */
class TemplatedModuleFileNamer implements ModuleFileNamer {

    /** The template to use to write the filenames */
    def template

    /**
     * Produce the File object that a Module will be written into
     * @param baseDir the base directory to write the modules into
     * @param module the module
     * @return the file to write to
     */
    @Override
    File writeFileName(File baseDir, Module module) {
        def engine = new SimpleTemplateEngine()
        def template = engine.createTemplate(this.template)

        def binding = ["name":module.moduleName]
        def templatedFilename = template.make(binding).toString()

        return new File(baseDir, templatedFilename)
    }
}
