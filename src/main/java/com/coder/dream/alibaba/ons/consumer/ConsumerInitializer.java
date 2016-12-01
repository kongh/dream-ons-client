package com.coder.dream.alibaba.ons.consumer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by konghang on 2016/11/27.
 */
@Singleton
public class ConsumerInitializer {

    @Inject
    private ConsumerService consumerService;

    /**
     * start
     */
    public void start(){
        consumerService.start();
    }

    /**
     * stop
     */
    public void stop(){
        consumerService.stop();
    }
}
