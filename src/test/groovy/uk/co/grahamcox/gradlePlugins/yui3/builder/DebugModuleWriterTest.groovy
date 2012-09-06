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
 * Unit tests for the Debug Module Writer
 */
class DebugModuleWriterTest {
    
    /**
     * Write an empty module - i.e. one with dependencies but no content
     */
    @Test
    public void testWriteEmpty() {
        Module module = new Module()
        module.moduleName = "empty"
        module.dependencies = ["base", "model"]

        ModuleWriter moduleWriter = new DebugModuleWriter()
        String written = moduleWriter.write(module)

        Assert.assertNotNull(written)

        String expected = """YUI.add("empty", function(Y) {}, "1.0.0", {requires: ["base","model"]});"""
        Assert.assertEquals(expected, written)
    }

    /**
     * Write a module that contains two files
     */
    @Test
    public void testWriteFiles() {
        Module module = new Module()
        module.moduleName = "empty"
        module.dependencies = ["base", "model"]
        module.moduleSources = [
            new File(getClass().getResource("/modules/simple/a").toURI()),
            new File(getClass().getResource("/modules/simple/b").toURI())
        ]

        ModuleWriter moduleWriter = new DebugModuleWriter()
        String written = moduleWriter.write(module)

        Assert.assertNotNull(written)

        String expected = """YUI.add("empty", function(Y) {abc\n123\n}, "1.0.0", {requires: ["base","model"]});"""
        Assert.assertEquals(expected, written)
    }

    @Test
    public void testWriteReal() {
        def moduleLoader = new ModuleLoader()
        def moduleFile = new File(getClass().getResource("/modules/working/model/build.properties").toURI())
        def expectedFile = new File(getClass().getResource("/modules/working/model/output.js").toURI())
        def module = moduleLoader.load(moduleFile)

        Assert.assertNotNull(module)

        ModuleWriter moduleWriter = new DebugModuleWriter()
        String written = moduleWriter.write(module)

        Assert.assertNotNull(written)

        String expected = expectedFile.text
        Assert.assertEquals(expected, written)
    }
}

