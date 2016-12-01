package com.coder.dream.alibaba.ons.producer;

import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

import java.util.Properties;

/**
 * Created by konghang on 2016/11/28.
 */
public abstract class DefaultProducerModule extends AbstractModule{

    protected MapBinder<String, ProducerProvider> mapBinder;

    protected ProducerConfig config;

    public DefaultProducerModule(ProducerConfig config) {
        this.config = config;
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
        mapBinder = MapBinder.newMapBinder(binder(), String.class, ProducerProvider.class);
    }

    /**
     * 配置过程
     */
    protected void doConfigure() {
        Properties properties = config.getProperties();
        ProducerProvider provider = new ProducerProvider(properties);
        binProducer(properties, provider);

        bind(ProducerService.class).to(OnsProducerService.class);
        bind(ProducerInitializer.class);
    }

    /**
     * 绑定producer
     *
     * @param pid
     * @param provider
     */
    protected void bindProducer(String pid, ProducerProvider provider){
        bind(Key.get(Producer.class, Names.named(pid))).toProvider(provider);
        mapBinder.addBinding(pid).toInstance(provider);
    }

    /**
     * 绑定producer
     *
     * @param properties
     * @param producerProvider
     */
    protected void binProducer(Properties properties, ProducerProvider producerProvider) {
        String property = properties.getProperty(PropertyKeyConst.ProducerId);
        bindProducer(property, producerProvider);
    }
}
