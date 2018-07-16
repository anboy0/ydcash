package com.demo.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BaseController {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected HttpServletResponse response;

    public Object getSessionByName(String name) {
        return request.getSession().getAttribute(name);
    }

    public void setSession(String name, Object obj) {
        request.getSession().setAttribute(name, obj);
    }

    public HttpSession getSession() {
        return request.getSession();
    }
}
