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

import org.easymock.EasyMock
import org.junit.Test

/**
 * Unit tests for the Chained Module builder
 */
class ChainedModuleBuilderTest {
    /**
     * Test that both delegate builders get called
     */
    @Test
    public void test() {
        Module module = new Module()
        module.moduleName = "empty"
        module.dependencies = ["base", "model"]
        File base = new File("/tmp")

        ModuleBuilder builder1 = EasyMock.createMock(ModuleBuilder)
        ModuleBuilder builder2 = EasyMock.createMock(ModuleBuilder)
        builder1.build(module, base)
        EasyMock.expectLastCall()
        builder2.build(module, base)
        EasyMock.expectLastCall()

        EasyMock.replay(builder1, builder2)

        ChainedModuleBuilder builder = new ChainedModuleBuilder()
        builder.moduleBuilders = [builder1, builder2]

        builder.build(module, base)

        EasyMock.verify(builder1, builder2)
    }
}
