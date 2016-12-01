package com.coder.dream.alibaba.ons.consumer;

import com.aliyun.openservices.ons.api.MessageListener;

/**
 * Created by konghang on 2016/12/1.
 */
public interface ConsumerService extends MessageListener{

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();
}
