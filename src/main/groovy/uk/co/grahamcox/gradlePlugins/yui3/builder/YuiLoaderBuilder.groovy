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
 * Builder to build the script file that acts as the Yui3 Loader
 */
class YuiLoaderBuilder {
    /**
     * Write the actual Yui3 Loader script
     * @param groupName the name of the group to produce
     * @param modules the modules to write the loader for
     * @return the loader
     */
    public String buildLoader(String groupName, String moduleBase, List<Module> modules) {
        StringBuilder builder = new StringBuilder()
        builder.append("var YUI_config = YUI_config || {};\n")
        builder.append("YUI_config.groups = YUI_config.groups || {};\n")
        builder.append("YUI_config.groups[\"${groupName}\"] = {\n")
        builder.append("\"base\": \"${moduleBase}\",\n")
        builder.append("\"modules\": {\n")
        builder.append(modules.collect {mod ->
            StringBuilder inner = new StringBuilder()
            inner.append("\"${mod.moduleName}\": {\n")
            inner.append("\"requires\": [")
            inner.append(mod.dependencies.collect {d -> "'${d}'"}.join(","))
            inner.append("],\n")
            inner.append("\"path\": \"/${mod.moduleName}/${mod.moduleName}.js\"\n")
            inner.append("}")
            return inner.toString()
        }.join(","))

        builder.append("}\n")
        builder.append("}\n")

        return builder.toString()
    }
}
