package com.coder.dream.alibaba.ons.config;

import com.google.inject.name.Named;
import org.apache.commons.lang3.StringUtils;

/**
 * MQ默认的配置
 *
 * Created by konghang on 2016/11/28.
 */
public class MqDefaultConfig {

    /**
     * 默认的生产组-一般一个应用设置
     */
    @Named(value = MqConfigConstants.KEY_PRODUCER_ID)
    private String defaultProducerId;

    /**
     * 默认的消费组
     */
    @Named(value = MqConfigConstants.KEY_CONSUMER_ID)
    private String defaultConsumerId;

    /**
     * ONS全局访问key
     */
    @Named(value = MqConfigConstants.KEY_ACCESS_KEY)
    private String accessKey;

    /**
     * ONS全局密码key
     */
    @Named(value = MqConfigConstants.KEY_SECRET_KEY)
    private String secretKey;

    /**
     * 检查配置
     */
    public void check(){
        String errorFormat = "%s,未配置";
        assert StringUtils.isNotBlank(defaultProducerId) : String.format(errorFormat,MqConfigConstants.KEY_PRODUCER_ID);
        assert StringUtils.isNotBlank(defaultConsumerId) : String.format(errorFormat,MqConfigConstants.KEY_CONSUMER_ID);
        assert StringUtils.isNotBlank(accessKey) : String.format(errorFormat,MqConfigConstants.KEY_ACCESS_KEY);
        assert StringUtils.isNotBlank(secretKey) : String.format(errorFormat,MqConfigConstants.KEY_SECRET_KEY);
    }

    public String getDefaultProducerId() {
        return defaultProducerId;
    }

    public void setDefaultProducerId(String defaultProducerId) {
        this.defaultProducerId = defaultProducerId;
    }

    public String getDefaultConsumerId() {
        return defaultConsumerId;
    }

    public void setDefaultConsumerId(String defaultConsumerId) {
        this.defaultConsumerId = defaultConsumerId;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
