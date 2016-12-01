package com.coder.dream.alibaba.ons.consumer;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.coder.dream.alibaba.ons.test.MessageListenerImpl;
import com.google.inject.Provider;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by konghang on 2016/11/27.
 */
public class ConsumerProvider implements Provider<Consumer> {

    private Consumer consumer;

    public ConsumerProvider() {
        Subscription subscription = new Subscription();
        subscription.setTopic("KH-Test-topic");
        subscription.setExpression("*");
        MessageListener messageListener = new MessageListenerImpl();

        ConsumerBean consumer = new ConsumerBean();
        this.consumer = consumer;

        consumer.setProperties(ConsumerConfig.getConsumer());
        Map<Subscription, MessageListener> subscriptionTable = new HashMap<Subscription, MessageListener>();
        subscriptionTable.put(subscription, messageListener);
        consumer.setSubscriptionTable(subscriptionTable);
    }

    @Override
    public Consumer get() {
        return consumer;
    }
}
