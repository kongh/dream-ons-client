package com.coder.dream.alibaba.ons.consumer;

import com.coder.dream.alibaba.ons.config.SubscriptionConfig;
import com.coder.dream.alibaba.ons.registry.ConsumerGuiceRegistry;
import com.coder.dream.alibaba.ons.registry.Registry;
import com.coder.dream.alibaba.ons.scan.ScanManager;
import com.coder.dream.alibaba.ons.scan.impl.DefaultScanManager;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;
import com.google.inject.AbstractModule;

import java.util.Map;
import java.util.Set;

/**
 * Created by konghang on 2016/11/28.
 */
public abstract class DefaultConsumerModule extends AbstractModule{

    protected Registry registry;

    protected ScanModel scanModel;

    public DefaultConsumerModule(ScanModel scanModel) {
        this.scanModel = scanModel;
    }

    @Override
    protected void configure() {
        doInitConfigure();
        doConfigure();
    }

    /**
     * 配置前的初始化步骤
     */
    protected void doInitConfigure() {
        registry = new ConsumerGuiceRegistry(binder());
    }

    /**
     * 配置过程
     */
    protected void doConfigure() {
        Map<String, Set<SubscriptionConfig>> subscriptions = scanModel.getSubscriptions();
        for(Map.Entry<String, Set<SubscriptionConfig>> entry : subscriptions.entrySet()){
            Set<SubscriptionConfig> subscriptionConfigs = entry.getValue();
            ConsumerProvider provider = new ConsumerProvider(subscriptionConfigs);
            binConsumer(entry.getKey(),provider);
        }

        bind(ScanManager.class).to(DefaultScanManager.class);
        bind(ConsumerService.class).to(OnsConsumerService.class);
        bind(ConsumerInitializer.class);
    }

    protected void binConsumer(String cid, ConsumerProvider producerProvider) {
        registry.registry(cid, producerProvider);
    }
}
