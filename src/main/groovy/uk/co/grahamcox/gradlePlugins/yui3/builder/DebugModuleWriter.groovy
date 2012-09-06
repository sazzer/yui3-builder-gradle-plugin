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
 * Write the Debug version of the Module out, by simply concatenating the files
 * and wrapping in the appropriate boilerplate
 */
class DebugModuleWriter implements ModuleWriter {
    /**
     * Write the provided module to the provided output stream
     * @param module The module to write
     * @return the contents of the module file
     * @throws IOException if the module couldn't be written
     */
    @Override
    String write(Module module) throws IOException {
        StringBuilder stringBuilder = new StringBuilder()
        stringBuilder.append("""YUI.add("${module.moduleName}", function(Y) {
}, "1.0.0", {
    requires: [""")

        stringBuilder.append(module.dependencies.collect { dep -> "\"${dep}\"" }.join(","))

        stringBuilder.append("""]
});""")
        
        return stringBuilder.toString()
    }
}


