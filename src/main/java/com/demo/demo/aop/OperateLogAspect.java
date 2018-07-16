package com.demo.demo.aop;

import com.demo.demo.log.bean.AbstractLogDict;
import com.demo.demo.log.bean.DefaultLogDict;

import java.lang.annotation.*;

/**
 * <p>功能描述：操作日志-切点</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月9日 下午2:16:41
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface OperateLogAspect {

    /**
     * 业务操作的名称
     *
     * @return
     */
    String value() default "";

    /**
     * 业务操作实体的主键
     *
     * @return
     */
    String key() default "id";

    /**
     * 字段和字段名称的映射，字段和方法名称的映射
     *
     * @return
     */
    Class<? extends AbstractLogDict> dict() default DefaultLogDict.class;
}
