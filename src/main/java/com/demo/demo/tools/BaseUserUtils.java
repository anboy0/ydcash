package com.demo.demo.tools;

import com.demo.demo.sys.entity.BaseUser;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseUserUtils {

    public static BaseUser getCurrentBaseUser() {

        return (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    
    public static Integer getCurrentBaseUserId() {
    	BaseUser user = (BaseUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if(user != null){
    		return user.getId();
    	}
    	
    	return  null;
    }
}
