package com.coder.dream.alibaba.ons.registry;

/**
 * 注册
 *
 * Created by konghang on 2016/11/28.
 */
public interface Registry<T> {

    /**
     * 注册
     *
     * @param key
     */
    void registry(String key, T instance);
}
