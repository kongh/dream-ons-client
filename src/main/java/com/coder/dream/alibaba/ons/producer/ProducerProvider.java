package com.coder.dream.alibaba.ons.producer;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.google.inject.Provider;

import java.util.Properties;

/**
 * Created by konghang on 2016/11/27.
 */
public class ProducerProvider implements Provider<Producer> {

    private Producer producer;

    public ProducerProvider(Properties properties) {
        this.producer = create(properties);
    }

    private Producer create(Properties properties) {
        ProducerBean producerBean = new ProducerBean();
        producerBean.setProperties(properties);
        return producerBean;
    }

    @Override
    public Producer get() {
        return producer;
    }
}
