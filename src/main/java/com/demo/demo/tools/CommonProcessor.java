package com.demo.demo.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

@Component
public class CommonProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    @Autowired
    UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    @Autowired
    UrlAccessDecisionManager urlAccessDecisionManager;

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
        o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
        o.setAccessDecisionManager(urlAccessDecisionManager);
        return o;
    }

}
