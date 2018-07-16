package com.demo.demo.exception;

import com.demo.demo.response.CommonStatus;
import com.demo.demo.response.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>功能描述：自定义异常拦截器</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 下午5:17:59
 */
@ControllerAdvice
public class RestExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(Throwable.class)
    public ResponseData handle(Throwable e) {
        e.printStackTrace();
        if (e instanceof RestException) {
            return new ResponseData(((RestException) e).getStatus());
        } else {
            ResponseData response = new ResponseData(CommonStatus.INTERNAL_ERROR);
            response.setData(String.valueOf(e.getMessage()));
            return response;
        }
    }
}