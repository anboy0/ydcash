package com.demo.demo.sysConfig;

import com.alibaba.fastjson.JSONObject;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ResponseData;
import com.demo.demo.sys.service.BaseUserService;
import com.demo.demo.tools.CommonProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BaseUserService baseUserService;
    @Autowired
    CommonProcessor commonProcessor;
    @Autowired
    FailureHandler failureHandler;
    @Autowired
    SuccessHandler successHandler;
    @Autowired
    SessionRegistry sessionRegistry;
    @Autowired
    LoutSuccessHan loutSuccessHan;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(baseUserService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers("/index.html", "/v2/api-docs", "/configuration/ui", "/swagger-resources*//**//**",
                "/configuration/security", "/swagger-ui.html", "/webjars*//**//**", "/base/config/login_p");
        //web.ignoring().anyRequest();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.formLogin().permitAll().usernameParameter("username").passwordParameter("password").failureHandler(failureHandler).successHandler(successHandler);
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.logout().logoutSuccessHandler(new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                String s = JSONObject.toJSONString(new ResponseData<>("退出成功", CommonStatus.OK));
                out.write(s);
                out.flush();
                out.close();
            }
        });
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                String s = JSONObject.toJSONString(new ResponseData<>(CommonStatus.FORBIDDEN));
                out.write(s);
                out.flush();
                out.close();
            }
        });
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                String s = JSONObject.toJSONString(new ResponseData<>("权限不足",CommonStatus.FORBIDDEN));
                out.write(s);
                out.flush();
                out.close();
            }
        });
        http.sessionManagement().invalidSessionUrl("/login");
        http.sessionManagement().maximumSessions(1).sessionRegistry(sessionRegistry).expiredUrl("/login");
    }

}
