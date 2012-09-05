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
 * Unit tests for the Module Loader
 */
class ModuleLoaderTest {
    def loader = new ModuleLoader()

    /**
     * Load a valid, simple module
     */
    @Test
    public void testLoadSimpleModule() {
        def file = new File(getClass().getResource("/modules/working/model/build.properties").toURI())
        def module = loader.load(file)

        Assert.assertNotNull(module)
        Assert.assertEquals("model", module.moduleName)
        Assert.assertEquals(2, module.dependencies.size())
        Assert.assertTrue(module.dependencies.contains("base"))
        Assert.assertTrue(module.dependencies.contains("model"))
        Assert.assertEquals(1, module.moduleSources.size())
        Assert.assertTrue(module.moduleSources.contains(new File(file.parentFile, "js/model.js")))
    }

    /**
     * Load a module with no source directory
     */
    @Test
    public void testLoadEmptyModule() {
        def file = new File(getClass().getResource("/modules/working/empty/build.properties").toURI())
        def module = loader.load(file)

        Assert.assertNotNull(module)
        Assert.assertEquals("empty", module.moduleName)
        Assert.assertEquals(1, module.dependencies.size())
        Assert.assertTrue(module.dependencies.contains("base"))
        Assert.assertEquals(0, module.moduleSources.size())
    }

    /**
     * Try to load a file that doesn't exist
     */
    @Test(expected = FileNotFoundException)
    public void loadMissingModule() {
        def file = new File(new File(getClass().getResource("/modules/").toURI()), "missing/build.properties")
        def module = loader.load(file)
    }

    /**
     * Try to load a module with a missing source file
     */
    @Test(expected = FileNotFoundException)
    public void loadBrokenModule() {
        def file = new File(getClass().getResource("/modules/broken/build.properties").toURI())
        def module = loader.load(file)
    }
}
