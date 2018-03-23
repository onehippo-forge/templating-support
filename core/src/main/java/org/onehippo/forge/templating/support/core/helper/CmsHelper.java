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
package org.onehippo.forge.templating.support.core.helper;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.request.HstRequestContext;

/**
 * For CMS related tags
 */
public final class CmsHelper {

    public static final CmsHelper INSTANCE = new CmsHelper();

    private CmsHelper() {
    }


    public String createCmsEditLink(final HippoBean bean) {
        final HstRequestContext requestContext = RequestContextProvider.get();
        if (invalid(bean, requestContext)) {
            return "";
        }

        return "";
    }

    public String createManageContentComment(final HippoBean bean) {
        final HstRequestContext requestContext = RequestContextProvider.get();
        if (invalid(bean, requestContext)) {
            return "";
        }

        return "";
    }

    public String createCmsEditMenuLink(final HippoBean bean) {
        final HstRequestContext requestContext = RequestContextProvider.get();
        if (invalid(bean, requestContext)) {
            return "";
        }

        return "";
    }

    private boolean invalid(final HippoBean bean, final HstRequestContext requestContext) {
        return requestContext == null || !requestContext.isCmsRequest() || bean == null;
    }
}