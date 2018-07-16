package com.demo.demo.sysConfig;

import com.alibaba.fastjson.JSON;
import com.demo.demo.sys.beans.ErrorBean;
import com.demo.demo.sys.enums.ErrorCode;
import com.demo.demo.tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UrlRoleInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(UrlRoleInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
         * 这里可查询数据库权限去判断是否有权限.
         * 返回false 后 postHandle 就不会被调用.
         * 请求类型PUT,GET,POST,DELETE
         */
        System.out.println(request.getMethod());
        Integer userId = (Integer) request.getSession().getAttribute(GlobalSession.USER_LOGIN_ID);
        if (Tools.isEmpty(userId)) {
            ErrorBean errorBean = ErrorCode.SESSION_ERROR.getResult();
            String ret = JSON.toJSONString(errorBean);
            returnJson(response, ret);
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        super.postHandle(request, response, handler, modelAndView);
    }

    private void returnJson(HttpServletResponse response, String json) throws Exception {
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(json);

        } catch (IOException e) {
            logger.error("response error", e);
        } finally {
            if (writer != null)
                writer.close();
        }
    }
}
