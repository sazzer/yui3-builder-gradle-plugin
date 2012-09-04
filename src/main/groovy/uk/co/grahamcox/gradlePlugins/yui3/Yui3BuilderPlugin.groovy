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

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.api.tasks.SourceSet
import org.gradle.api.DefaultTask
import org.gradle.tooling.internal.provider.TasksFactory
import org.gradle.api.Task
import org.gradle.api.plugins.WarPlugin

/**
 * The main Plugin class for the YUI3 Builder plugin
 */
class Yui3BuilderPlugin implements Plugin<Project> {
    /** The logger to use */
    private static final Logger LOG = Logging.getLogger(Yui3BuilderPlugin.class)

    /**
     * Apply this plugin to the given target object
     * @param project the Project target
     */
    @Override
    void apply(Project project) {
        LOG.debug("Building YUI3 modules for project {}", project)

        if (!project.convention.plugins.yui3) {
            Yui3Convention pluginConvention = new Yui3Convention(project)
            project.convention.plugins.yui3 = pluginConvention
        }

        addTasks(project)
    }

    /**
     * Add all of the required tasks to the project
     * @param project the project
     */
    private void addTasks(Project project) {
        addBuildTask(project)
    }

    /**
     * Add a task to the project
     * @param project the project to add the task to
     * @param name the name of the task
     * @param task the class representing the task
     * @return the task
     */
    private <T extends Task> T addTask(Project project, String name, Class<T> task) {
        return project.tasks.add(name, task)
    }

    /**
     * Add the Build task to the project
     * @param project the project
     */
    private void addBuildTask(Project project) {
        addTask project, "yui3", Yui3BuildTask
        project.tasks.getByName("processResources").dependsOn("yui3")
    }

}
