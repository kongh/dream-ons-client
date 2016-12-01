package com.coder.dream.alibaba.ons.config;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 订阅配置
 *
 * Created by konghang on 2016/12/1.
 */
public class SubscriptionConfig<T> implements Serializable{

    //订阅组
    private String cid;

    //类
    private Class<T> clazz;

    //方法
    private Set<Method> methods;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public void setClazz(Class<T> clazz) {
        this.clazz = clazz;
    }

    public Set<Method> getMethods() {
        return methods;
    }

    public void setMethods(Set<Method> methods) {
        this.methods = methods;
    }
}
