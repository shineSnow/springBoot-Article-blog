package com.zqy.blog_admin.system.utils;

import java.util.Map;

public class BeanMapUtilByApache {

    /**
     * 对象转Map
     * @param object
     * @return
     */
    public static Map beanToMap(Object object){
        return new org.apache.commons.beanutils.BeanMap(object);
    }
    /**
     * map转对象
     * @param map
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map map, Class<T> beanClass) throws Exception {
        T object = beanClass.newInstance();
        org.apache.commons.beanutils.BeanUtils.populate(object, map);
        return object;
    }
}
