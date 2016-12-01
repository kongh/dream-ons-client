package com.coder.dream.alibaba.ons.producer;

import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.coder.dream.alibaba.ons.config.MqConfig;

import java.util.Properties;

/**
 * Created by konghang on 2016/11/27.
 */
public interface ProducerConfig {

    Properties getProperties();
//    public Properties getProperties(){
//        Properties properties = new Properties();
//        properties.setProperty(PropertyKeyConst.ProducerId, MqConfig.PRODUCER_ID);
//        properties.setProperty(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
//        properties.setProperty(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
//        properties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
//        return properties;
//    }
}
