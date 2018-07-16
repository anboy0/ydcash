package com.demo.demo.sysConfig;

import com.alibaba.fastjson.JSONObject;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ResponseData;
import com.demo.demo.tools.BaseUserUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        System.out.println(request.getSession().getId());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        String s = JSONObject.toJSONString(new ResponseData<>(JSONObject.toJSON(BaseUserUtils.getCurrentBaseUser()), CommonStatus.OK));
        out.write(s);
        out.flush();
        out.close();
    }
}
