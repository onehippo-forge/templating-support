/*
 * Copyright 2018 Hippo B.V. (http://www.onehippo.com)
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

package org.onehippo.forge.templating.support.thymeleaf.servlet.attributes;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.model.IAttribute;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.standard.expression.IStandardExpression;
import org.thymeleaf.standard.expression.IStandardExpressionParser;
import org.thymeleaf.standard.expression.StandardExpressions;
import org.thymeleaf.templatemode.TemplateMode;

public abstract class ThymeleafHstAttribute extends AbstractAttributeTagProcessor {

    private static final int PRECEDENCE = 10000;

    public static final String ATTR_FULLY_QUALIFIED = "hst:fullyQualified";
    public static final String ATTR_RESOURCE_ID = "hst:resourceId";

    public ThymeleafHstAttribute(final String dialectPrefix, final String attributeName) {
        super(TemplateMode.HTML, dialectPrefix, null, false, attributeName, true, PRECEDENCE, true);
    }

    @SuppressWarnings("unchecked")
    protected <T> T getExpression(final ITemplateContext context, final String attributeValue) {
        final IEngineConfiguration configuration = context.getConfiguration();
        final IStandardExpressionParser parser = StandardExpressions.getExpressionParser(configuration);
        final IStandardExpression expression = parser.parseExpression(context, attributeValue);
        return (T) expression.execute(context);
    }

    protected boolean parseBoolean(final IAttribute attribute) {
        if (attribute == null) {
            return false;
        }
        final String value = attribute.getValue();
        return Boolean.parseBoolean(value);
    }

    protected void setLink(final IElementTagStructureHandler structureHandler, final IProcessableElementTag tag, final String link) {
        final String tagName = tag.getElementCompleteName();
        switch (tagName) {
            case "a":
            case "link":
                structureHandler.setAttribute("href", link);
                break;
            case "script":
                structureHandler.setAttribute("src", link);
                break;
            case "form":
                structureHandler.setAttribute("form", link);
                break;
            default:
                structureHandler.setAttribute("href", link);
        }
    }

}
