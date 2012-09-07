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
 * Unit tests for the Module Builder
 */
class ModuleBuilderTest {
    /** The builder to test */
    def builder

    /**
     * Set up the builder to use
     */
    @Before
    public void setup() {
        builder = new ModuleBuilder()
        builder.moduleWriter = new DebugModuleWriter()
        builder.moduleFileNamer = new TemplatedModuleFileNamer()
        builder.moduleFileNamer.template = '${name}/${name}.js';
    }

    /**
     * Test writing a module with no contents, only dependencies
     */
    @Test
    public void testEmptyModule() {
        Module module = new Module()
        module.moduleName = "empty"
        module.dependencies = ["base", "model"]

        File temp = File.createTempFile("base", Long.toString(System.nanoTime()))
        File output = new File(temp, "empty/empty.js")
        try {
            temp.delete()
            temp.mkdir()

            builder.build(module, temp)

            Assert.assertEquals("""YUI.add("empty", function(Y) {}, "1.0.0", {requires: ["base","model"]});""", output.text)
        }
        finally {
            output.delete()
            output.parentFile.delete()
            temp.delete()
        }
    }

    /**
     * Test writing a module with contents
     */
    @Test
    public void testSimpleModule() {
        Module module = new Module()
        module.moduleName = "empty"
        module.dependencies = ["base", "model"]
        module.moduleSources = [
                new File(getClass().getResource("/modules/simple/a").toURI()),
                new File(getClass().getResource("/modules/simple/b").toURI())
        ]

        File temp = File.createTempFile("base", Long.toString(System.nanoTime()))
        File output = new File(temp, "empty/empty.js")
        try {
            temp.delete()
            temp.mkdir()

            builder.build(module, temp)

            String expected = """YUI.add("empty", function(Y) {abc\n123\n}, "1.0.0", {requires: ["base","model"]});"""
            Assert.assertEquals(expected, output.text)
        }
        finally {
            output.delete()
            output.parentFile.delete()
            temp.delete()
        }
    }
}


