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

import com.yahoo.platform.yui.compressor.JavaScriptCompressor
import org.mozilla.javascript.ErrorReporter
import org.slf4j.LoggerFactory
import org.slf4j.Logger
import org.mozilla.javascript.EvaluatorException

/**
 * Implementation of the ModuleWriter class that gets the string to write from another
 * ModuleWriter and minifies it on the way out
 */
class MinifiedModuleWriter extends TransformingModuleWriter {

    /**
     * Transform the written module
     * @param input the untransformed module
     * @return the transformed module
     */
    protected String transform(String input) {
        Reader reader = new StringReader(input)
        ErrorReporter errorReporter = new LoggingErrorReporter()
        StringWriter writer = new StringWriter()

        JavaScriptCompressor compressor = new JavaScriptCompressor(reader, errorReporter)
        compressor.compress(writer, -1, false, false, true, false)

        writer.flush()
        return writer.toString()
    }

    class LoggingErrorReporter implements ErrorReporter {
        private static final Logger LOG = LoggerFactory.getLogger(MinifiedModuleWriter)

        @Override
        void warning(String message, String sourceName,
                     int line, String lineSource, int lineOffset) {
            LOG.warn("{} at line {}:{} of {}: {}", message, line, lineOffset, sourceName, lineSource)
        }

        @Override
        void error(String message, String sourceName,
                   int line, String lineSource, int lineOffset) {
            LOG.error("{} at line {}:{} of {}: {}", message, line, lineOffset, sourceName, lineSource)
        }

        @Override
        EvaluatorException runtimeError(String message, String sourceName,
                                        int line, String lineSource, int lineOffset) {
            error(message, sourceName, line, lineSource, lineOffset)
            return new EvaluatorException(message)
        }
    }
}
