package com.coder.dream.alibaba.ons.consumer;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.coder.dream.alibaba.ons.config.MqConfig;

import java.util.Properties;

/**
 * Created by konghang on 2016/11/27.
 */
public class ConsumerConfig {


    public static Properties getConsumer(){
        Properties producerProperties = new Properties();
        producerProperties.setProperty(PropertyKeyConst.ConsumerId, MqConfig.CONSUMER_ID);
        producerProperties.setProperty(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
        producerProperties.setProperty(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
        producerProperties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
        return producerProperties;
    }
}
