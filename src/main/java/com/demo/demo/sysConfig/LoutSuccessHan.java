package com.demo.demo.sysConfig;

import com.alibaba.fastjson.JSONObject;
import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ResponseData;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by rzh on 18-3-8.
 */
@Component
public class LoutSuccessHan implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        String s = JSONObject.toJSONString(new ResponseData<>(CommonStatus.LOGOUT_SUCCESS));
        out.write(s);
        out.flush();
        out.close();
    }
}
