package com.coder.dream.alibaba.ons.registry;

import com.coder.dream.alibaba.ons.consumer.ConsumerProvider;
import com.google.inject.Binder;
import com.google.inject.multibindings.MapBinder;

/**
 * ConsumerProvider Guice注册
 *
 * Created by konghang on 2016/11/28.
 */
public class ConsumerGuiceRegistry implements Registry<ConsumerProvider>{

    protected MapBinder<String, ConsumerProvider> mapBinder;

    public ConsumerGuiceRegistry(Binder binder) {
        mapBinder = MapBinder.newMapBinder(binder, String.class, ConsumerProvider.class);
    }

    @Override
    public void registry(String key, ConsumerProvider instance) {
        bindProducer(key, instance);
    }

    /**
     * 绑定producer
     *
     * @param pid
     * @param provider
     */
    protected void bindProducer(String pid, ConsumerProvider provider){
        mapBinder.addBinding(pid).toInstance(provider);
    }
}
