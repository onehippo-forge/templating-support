/*
 * Copyright 2019 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onehippo.forge.templating.support.pebble.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.BooleanUtils;
import org.onehippo.forge.templating.support.core.servlet.AbstractHstTemplateServlet;
import org.onehippo.forge.templating.support.pebble.servlet.loader.PebbleWebFileLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class PebbleHstTemplateServlet extends AbstractHstTemplateServlet {

    private static final Logger log = LoggerFactory.getLogger(PebbleHstTemplateServlet.class);
    private PebbleEngine engine;

    @Override
    protected void initializeTemplateEngine(final ServletConfig config) {

        final boolean cacheEnabled = BooleanUtils.toBoolean(config.getInitParameter(PARAM_CACHE_ENABLED));
        final boolean autoEscape = BooleanUtils.toBoolean(config.getInitParameter(PARAM_AUTO_ESCAPE_ENABLED));
        engine = new PebbleEngine.Builder()
                .cacheActive(cacheEnabled)
                .autoEscaping(autoEscape)
                .loader(new PebbleWebFileLoader())
                .extension(new PebbleHstExtension())
                .build();
    }

    @Override
    protected void clearTemplateCache() {
        engine.getTemplateCache().invalidateAll();
    }

    @Override
    protected Object createTemplateContext(final HttpServletRequest request, final HttpServletResponse response) {
        return new HashMap<String, Object>();
    }

    @Override
    protected void processTemplate(final HttpServletRequest request, final HttpServletResponse response, final String templatePath, final Object context) throws IOException {
        final PebbleTemplate template = engine.getTemplate(templatePath);
        log.debug("Pebble template: {}", templatePath);
        if (template != null) {
            @SuppressWarnings("unchecked")
            final Map<String, Object> ourContext = (Map<String, Object>) context;
            template.evaluate(response.getWriter(), ourContext, request.getLocale());
        } else {
            // TODO remove, development only block
            throw new IllegalArgumentException("template was null for: " + templatePath);
        }
    }
}