package com.mnm.JuniorKnowledgeBaseTool.configuration;

import org.springframework.security.web.savedrequest.HttpSessionRequestCache;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CustomRequestCache extends HttpSessionRequestCache {

    public void saveRequest(HttpServletRequest req, HttpServletResponse resp) {
        if(!SecurityUtils.isFrameworkInternalRequest(req)) {
            super.saveRequest(req, resp);
        }
    }
}
