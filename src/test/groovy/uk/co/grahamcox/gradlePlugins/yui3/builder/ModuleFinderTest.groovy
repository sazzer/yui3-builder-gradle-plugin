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

import org.junit.Before
import org.junit.Test
import org.junit.Assert

/**
 * Unit tests for the Module Filder
 */
class ModuleFinderTest {
    def moduleFinder

    @Before
    void setup() {
        moduleFinder = new ModuleFinder()
        moduleFinder.moduleLoader = new ModuleLoader()
    }

    @Test
    void findModules() {
        def base = new File(getClass().getResource("/modules/working/").toURI())
        def modules = moduleFinder.findModules(base)

        Assert.assertNotNull(modules)
        Assert.assertEquals(2, modules.size())
        def expectedNames = ["empty", "model"]
        modules.each {m ->
            Assert.assertTrue(expectedNames.contains(m.moduleName))
            expectedNames.remove(m.moduleName)
        }
        Assert.assertTrue(expectedNames.isEmpty())
    }
}
