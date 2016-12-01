package com.coder.dream.alibaba.ons.consumer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.Set;

/**
 * Created by konghang on 2016/11/27.
 */
@Singleton
public class ConsumerInitializer {

    @Inject
    private Set<ConsumerProvider> consumerProviders;

    /**
     * start
     */
    public void start(){
        if(consumerProviders != null){
            for(ConsumerProvider consumerProvider : consumerProviders){
                consumerProvider.get().start();
            }
        }
    }

    /**
     * stop
     */
    public void stop(){
        if(consumerProviders != null){
            for(ConsumerProvider consumerProvider : consumerProviders){
                consumerProvider.get().shutdown();
            }
        }
    }
}
