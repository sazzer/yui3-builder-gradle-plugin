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
package uk.co.grahamcox.gradlePlugins.yui3

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.gradle.api.plugins.WarPlugin
import org.gradle.api.tasks.bundling.War

/**
 * The actual task to build the YUI3 modules
 */
class Yui3BuildTask extends DefaultTask {

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
        def toPath = new File(convention.toPath)
        if (!toPath.exists()) {
            toPath.mkdirs()
        }
        def log = new File(toPath, "log")
        log.write("Testing")
    }
}
