package com.coder.dream.alibaba.ons.registry;

import com.coder.dream.alibaba.ons.producer.ProducerProvider;
import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.multibindings.Multibinder;

/**
 * ProducerProvider Guice注册
 *
 * Created by konghang on 2016/11/28.
 */
public class ProducerGuiceRegistry implements Registry<ProducerProvider>{

    private Multibinder<ProducerProvider> multibinder;

    @Inject
    private Injector injector;

    public ProducerGuiceRegistry(Binder binder) {
        this.multibinder = Multibinder.newSetBinder(binder, ProducerProvider.class);
    }

    @Override
    public void registry(ProducerProvider instance) {
        multibinder.addBinding().toInstance(instance);
    }
}
