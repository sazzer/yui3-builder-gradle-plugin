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

import org.junit.Test
import org.junit.Assert

/**
 * Unit tests for the TemplatedModuleFileNamer
 */
class TemplatedModuleFileNamerTest {
    /**
     * Test writing a simple module name
     */
    @Test
    public void testRaw() {
        TemplatedModuleFileNamer namer = new TemplatedModuleFileNamer()
        namer.template = '${name}/${name}.js'

        Module module = new Module()
        module.moduleName = "simple"

        File base = new File("/tmp/moduleBase")
        File expected = new File("/tmp/moduleBase/simple/simple.js")

        Assert.assertEquals(expected, namer.writeFileName(base, module))
    }
    /**
     * Test writing a slightly more complicated module name
     */
    @Test
    public void testMin() {
        TemplatedModuleFileNamer namer = new TemplatedModuleFileNamer()
        namer.template = '${name}/${name}-min.js'

        Module module = new Module()
        module.moduleName = "simple"

        File base = new File("/tmp/moduleBase")
        File expected = new File("/tmp/moduleBase/simple/simple-min.js")

        Assert.assertEquals(expected, namer.writeFileName(base, module))
    }
}
