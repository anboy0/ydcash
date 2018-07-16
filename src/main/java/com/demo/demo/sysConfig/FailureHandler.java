package com.demo.demo.sysConfig;

import com.alibaba.fastjson.JSONObject;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ResponseData;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class FailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String msg = "登录失败!";
//        if (e instanceof UsernameNotFoundException || e instanceof BadCredentialsException) {
//            msg = "用户名或密码输入错误，登录失败!";
//        } else if (e instanceof DisabledException) {
//            msg = "账户被禁用，登录失败，请联系管理员!";
//        } else {
//            msg = "登录失败!";
//        }
        String s = JSONObject.toJSONString(new ResponseData<>(msg, CommonStatus.CONTINUE));
        out.write(s);
        out.flush();
        out.close();
    }
}
