package com.demo.demo.tools;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * <p>功能描述：日期工具类</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 杭州凯立通信有限公司</p>
 *
 * @author PF0P65Z6
 * @version 1.0 2018年1月10日 上午9:57:38
 */
public class DateUtil {
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
    public static String getNowDate() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    public static String getNowTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String formatDate(Date date, String pattern) {
        String formatDate = null;
        if (StringUtils.isNotBlank(pattern)) {
            formatDate = DateFormatUtils.format(date, pattern);
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }
    
	public static Date getStrToDateTime(String str) throws ParseException{
		return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[]{DATETIME_PATTERN});
	}
	public static Date getStrToDate(String str) throws ParseException{
		return org.apache.commons.lang.time.DateUtils.parseDate(str, new String[]{DATE_PATTERN});
	}

}
