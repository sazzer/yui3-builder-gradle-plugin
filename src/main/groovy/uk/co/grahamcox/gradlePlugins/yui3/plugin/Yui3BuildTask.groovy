/*
 * Copyright (C) 02/09/12 graham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.grahamcox.gradlePlugins.yui3.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import uk.co.grahamcox.gradlePlugins.yui3.builder.Yui3Builder
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The actual task to build the YUI3 modules
 */
class Yui3BuildTask extends DefaultTask {
    /** The logger to use */
    private static final Logger LOG = LoggerFactory.getLogger(Yui3BuildTask)

    /**
     * Construct the task
     */
    public Yui3BuildTask() {
        description = "Build the YUI3 modules"
    }

    /**
     * The main action for the task
     */
    @TaskAction
    def generateModules() {
        def convention = this.project.convention.plugins.yui3
        def toPath = new File(convention.toPath, convention.outputBase)
        def fromPath = new File(convention.fromPath)
        if (!toPath.exists()) {
            toPath.mkdirs()
        }

        LOG.info("About to build modules from {} into {}", fromPath, toPath)

        def builder = new Yui3Builder()
        builder.to = toPath
        builder.from = fromPath
        builder.loaderBase = convention.loaderBase
        builder.groupName = convention.groupName
        builder.loaderFile = new File(toPath, convention.loaderFilename)

        builder.build()
    }
}
