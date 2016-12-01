package com.coder.dream.alibaba.ons.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Map;

/**
 * Created by konghang on 2016/12/1.
 */
@Singleton
public class OnsProducerService implements ProducerService {

    @Inject
    private Map<String, ProducerProvider> providers;

    @Override
    public SendResult send(ProducerRegister register, Message message) {
        ProducerProvider provider = providers.values().iterator().next();
        return provider.get().send(message);
    }

    @Override
    public void sendOneway(ProducerRegister register, Message message) {
        ProducerProvider provider = providers.values().iterator().next();
        provider.get().sendOneway(message);
    }

    @Override
    public void sendAsync(ProducerRegister register, Message message, SendCallback sendCallback) {
        ProducerProvider provider = providers.values().iterator().next();
        provider.get().sendAsync(message, sendCallback);
    }

    @Override
    public void start() {
        if(providers != null && !providers.isEmpty()) {
            for(ProducerProvider provider : providers.values()) {
                provider.get().start();
            }
        }
    }

    @Override
    public void stop() {
        if(providers != null && !providers.isEmpty()) {
            for(ProducerProvider provider : providers.values()) {
                provider.get().shutdown();
            }
        }
    }
}
