package com.coder.dream.alibaba.ons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 订阅关系
 *
 * Created by konghang on 2016/12/1.
 */
@Target({ElementType.METHOD })
@Retention(RUNTIME)
public @interface Subscription {

    /**
     * 主题
     *
     * @return
     */
    String topic();

    /**
     * tag标签，支持通配符
     *
     * 一般建议一个tag一个表达式
     * @return
     */
    String expression() default "*";
}
