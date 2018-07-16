package com.demo.demo.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.demo.demo.log.LogObjectHolder;
import com.demo.demo.log.bean.AbstractLogDict;
import com.demo.demo.log.enums.LogSucceed;
import com.demo.demo.sys.entity.OperateLog;
import com.demo.demo.sys.service.IOperateLogService;
import com.demo.demo.tools.BaseUserUtils;
import com.demo.demo.tools.CompareUtil;
import com.demo.demo.tools.HttpUtil;
import com.demo.demo.tools.ReflectUtil;

/**
 * <p>功能描述：AOP记录操作日志</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午10:10:35
 */
@Aspect
@Component

public class OperateLogAop {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IOperateLogService operateLogService;

    /**
     * controller层切点
     */
    @Pointcut(value = "@annotation(com.demo.demo.aop.OperateLogAspect)")
    public void controllerAspect() {
    }

    /**
     * 环绕通知 用于拦截controller层，记录用户的操作日志
     *
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("controllerAspect()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("AOP写日志报错!", e);
        }

        return result;
    }

    /**
     * 记录操作日志的业务处理
     *
     * @param point
     * @throws Exception
     */
    private void handle(ProceedingJoinPoint point) throws Exception {
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());

        //获取拦截方法的参数
        Object[] params = point.getArgs();
        Map<String, String> obj2 = null;
        if (params != null && params.length > 0) {
            obj2 = ReflectUtil.objectToMap(params[0]);
        }
        //如果当前用户未登录，不做日志
        Integer userId = BaseUserUtils.getCurrentBaseUserId();
        if (null == userId) {
            return;
        }

        //获取操作名称
        OperateLogAspect annotation = currentMethod.getAnnotation(OperateLogAspect.class);
        String operateName = annotation.value();
        String key = annotation.key();
        Class dictClass = annotation.dict();

        //如果涉及到修改,比对变化
        JSONObject msgJsonObj = null;
        AbstractLogDict logDict = (AbstractLogDict) dictClass.newInstance();
        if (operateName.indexOf("修改") != -1 || operateName.indexOf("编辑") != -1) {
            Object obj1 = LogObjectHolder.me().get();
            if (obj1 != null) {
                msgJsonObj = CompareUtil.compareObj(dictClass, key, obj1, obj2);
            } else {
                msgJsonObj = CompareUtil.parseKey(logDict, key, obj2);
            }
        } else {
            msgJsonObj = CompareUtil.parseKey(logDict, key, obj2);
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddr = HttpUtil.getIpAddr(request);

        //日志写入数据库
        OperateLog operateLog = new OperateLog();
        operateLog.setKey(CompareUtil.getKey(logDict, key, obj2));
        operateLog.setLogtype("1");
        operateLog.setLogname(operateName);
        operateLog.setMessage(msgJsonObj.toJSONString());
        operateLog.setSucceed(LogSucceed.SUCCESS.getMessage());
        operateLog.setCreateBy(userId);
        operateLog.setCreateTime(new Date());
        operateLog.setIpAddr(ipAddr);
        operateLogService.insert(operateLog);
    }
}