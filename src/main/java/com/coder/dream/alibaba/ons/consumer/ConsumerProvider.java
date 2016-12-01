package com.coder.dream.alibaba.ons.consumer;

import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.MessageListener;
import com.aliyun.openservices.ons.api.bean.ConsumerBean;
import com.aliyun.openservices.ons.api.bean.Subscription;
import com.coder.dream.alibaba.ons.config.SubscriptionConfig;
import com.google.inject.Provider;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by konghang on 2016/11/27.
 */
public class ConsumerProvider implements Provider<Consumer> {

    private Consumer consumer;

    private Set<SubscriptionConfig> subscriptions;

    public ConsumerProvider(Set<SubscriptionConfig> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public synchronized Consumer init(MessageListener messageListener){
        if(consumer != null){
            return consumer;
        }

        ConsumerBean consumer = new ConsumerBean();
        this.consumer = consumer;
        consumer.setProperties(ConsumerConfig.getConsumer());

        Map<Subscription, MessageListener> subscriptionTable = new HashMap<Subscription, MessageListener>();
        for(SubscriptionConfig config : subscriptions){
            Set<Method> methods = config.getMethods();
            for (Method method : methods) {
                com.coder.dream.alibaba.ons.annotation.Subscription declaredSubscription = method.getDeclaredAnnotation(com.coder.dream.alibaba.ons.annotation.Subscription.class);
                Subscription subscription = new Subscription();
                subscription.setTopic(declaredSubscription.topic());
                subscription.setExpression(declaredSubscription.expression());
                subscriptionTable.put(subscription, messageListener);
            }
        }
        consumer.setSubscriptionTable(subscriptionTable);
        return consumer;
    }

    @Override
    public Consumer get() {
        return consumer;
    }

    public Set<SubscriptionConfig> getSubscriptions(){
        return subscriptions;
    }
}
