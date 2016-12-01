package com.coder.dream.alibaba.ons.registry;

import com.aliyun.openservices.ons.api.Producer;
import com.coder.dream.alibaba.ons.producer.ProducerProvider;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.multibindings.MapBinder;
import com.google.inject.name.Names;

/**
 * ProducerProvider Guice注册
 *
 * Created by konghang on 2016/11/28.
 */
public class ProducerGuiceRegistry implements Registry<ProducerProvider>{

    protected MapBinder<String, ProducerProvider> mapBinder;

    private Binder binder;

    public ProducerGuiceRegistry(Binder binder) {
        this.binder = binder;
        mapBinder = MapBinder.newMapBinder(binder, String.class, ProducerProvider.class);
    }

    @Override
    public void registry(String key, ProducerProvider instance) {
        bindProducer(key, instance);
    }

    /**
     * 绑定producer
     *
     * @param pid
     * @param provider
     */
    protected void bindProducer(String pid, ProducerProvider provider){
        binder.bind(Key.get(Producer.class, Names.named(pid))).toProvider(provider);
        mapBinder.addBinding(pid).toInstance(provider);
    }
}
