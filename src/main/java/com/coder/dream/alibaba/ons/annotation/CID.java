package com.coder.dream.alibaba.ons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 消费分组
 *
 * 一个应用应该仅有一个消费分组
 *
 * 为了支持现有一个模块一个消费分组，特定义消费分组
 * Created by konghang on 2016/12/1.
 */
@Target({ ElementType.TYPE})
@Retention(RUNTIME)
public @interface CID {

    /**
     * 如果value=default，将使用系统配置的CID，这也是推荐的使用方式
     *
     * @return
     */
    String value() default "default";
}
