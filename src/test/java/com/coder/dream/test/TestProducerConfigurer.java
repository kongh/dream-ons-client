package com.coder.dream.test;

import com.coder.dream.alibaba.ons.config.MqConfig;
import com.coder.dream.alibaba.ons.producer.ProducerRegister;

/**
 * Mq Producer Configurer
 *
 * 注册
 * Created by konghang on 2016/11/29.
 */
public enum TestProducerConfigurer implements ProducerRegister{

    TRADE_CREATE_EVENT(MqConfig.PRODUCER_ID,MqConfig.TOPIC, MqConfig.TAG);
    ;

    TestProducerConfigurer(String pid, String topic, String tag) {
        this.pid = pid;
        this.topic = topic;
        this.tag = tag;
    }

    //分布组
    private String pid;

    //主题
    private String topic;

    //标签
    private String tag;

    @Override
    public String getPid() {
        return pid;
    }

    public String getTopic() {
        return topic;
    }

    public String getTag() {
        return tag;
    }
}
