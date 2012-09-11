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

import org.gradle.api.Project
import org.gradle.api.plugins.WarPlugin

/**
 * Convention object for the YUI3 plugin
 */
class Yui3Convention {
    /** The path to read the modules from */
    def fromPath = "src/main/javascript"

    /** The path to copy the module to */
    def toPath

    /** The filename of the loader file to write */
    def loaderFilename = "loader.js"

    /** The base to use in the loader file */
    def loaderBase = "js"

    /** The directory underneath the toPath to write the modules to */
    def outputBase = "js"

    /** The group name to use for the modules */
    def groupName = "yui3builder"

    /**
     * Configure the directory from which the modules should be loaded
     * @param path the path to load from
     */
    def from(String path) {
        this.fromPath = path
    }

    /**
     * Configure the directory into which the modules should be built
     * @param path the name of the directory
     */
    def into(String path) {
        this.toPath = path
    }

    /**
     * Set the YUI Loader Group Name to use
     * @param name the group name to use
     */
    def groupName(String name) {
        this.groupName = name
    }
}
