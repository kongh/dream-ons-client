package com.coder.dream.alibaba.ons.producer;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Created by konghang on 2016/11/27.
 */
@Singleton
public class ProducerInitializer {

    @Inject
    private ProducerService producerService;

    /**
     * 启动
     */
    public synchronized void start(){
        if(producerService != null){
            producerService.start();
        }
    }

    /**
     * 停止
     */
    public synchronized void stop(){
        if(producerService != null) {
            producerService.stop();
        }
    }
}
