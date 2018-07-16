package com.demo.demo.tools;

import com.demo.demo.tools.Annotation.CheckField;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tools {
    private static final String HEX_STRING = "0123456789abcdef";

    public static String MD5(String str) {
        try {
            MessageDigest ds = MessageDigest.getInstance("MD5");
            byte[] dat = ds.digest(str.getBytes());
            return Bytes2HexString(dat);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节转16进制字符串
     *
     * @param b
     * @return
     */
    public static String Byte2HexString(byte b) {
        StringBuffer sb = new StringBuffer();
        int a = (b & 0xff) >> 4;
        sb.append(HEX_STRING.charAt(a));
        a = b & 0xf;
        sb.append(HEX_STRING.charAt(a));
        return sb.toString();
    }

    /**
     * 字节数字转16进制字符串
     *
     * @param a
     * @return
     */
    public static String Bytes2HexString(byte[] a) {
        StringBuffer sb = new StringBuffer();
        for (byte b : a
                ) {
            sb.append(Byte2HexString(b));
        }
        return sb.toString();
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null)
            return true;
        if (obj instanceof String) {
            if (obj.equals(""))
                return true;
            else
                return false;
        }
        if (obj instanceof List) {
            if (((List) obj).size() == 0)
                return true;
        }
        return false;
    }

    public static boolean isFieldEmpty(Object obj) {
        Class cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            CheckField[] checkFields = f.getAnnotationsByType(CheckField.class);
            if (checkFields != null && checkFields.length > 0) {
                Method[] method = cls.getDeclaredMethods();
                for (Method m : method) {
                    if (m.getName().toLowerCase().equals("get" + f.getName().toLowerCase())) {
                        try {
                            Object v = m.invoke(obj);
                            if (v == null) {
                                return true;
                            }
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        return false;
    }

    public static Map<String,Object> trimHashMap(Map<String,Object> info){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        for(Map.Entry<String,Object> entry:info.entrySet()){
            if(entry.getValue()!=null){
                if(!((entry.getValue() instanceof String) && entry.getValue().equals(""))){
                    hashMap.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return hashMap;
    }
}
