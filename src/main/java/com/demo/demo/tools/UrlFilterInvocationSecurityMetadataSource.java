package com.demo.demo.tools;

import com.demo.demo.sys.entity.BaseMenu;
import com.demo.demo.sys.entity.BaseRole;
import com.demo.demo.sys.service.BaseMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Autowired
    BaseMenuService baseMenuService;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        if ("/login_p".equals(requestUrl)) {
            return null;
        }
        List<BaseMenu> allBaseMenu = baseMenuService.getAllBaseMenu();
        for (BaseMenu baseMenu : allBaseMenu) {
            if (antPathMatcher.match(baseMenu.getUrl(), requestUrl) && baseMenu.getBaseRoles().size() > 0) {
                List<BaseRole> baseRoles = baseMenu.getBaseRoles();
                int size = baseRoles.size();
                String[] values = new String[size];
                for (int i = 0; i < size; i++) {
                    values[i] = baseRoles.get(i).getName();
                }
                return SecurityConfig.createList(values);
            }
        }
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
