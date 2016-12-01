package com.coder.dream;

import com.coder.dream.alibaba.ons.consumer.ConsumerInitializer;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;
import com.coder.dream.alibaba.ons.scan.strategy.impl.DefaultScanStrategy;
import com.coder.dream.test.TestConsumerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.TestCase;

import java.util.Arrays;

/**
 * Created by konghang on 2016/12/1.
 */
public class SubscriptionTest extends TestCase {

    public void test1(){
        DefaultScanStrategy scanStrategy = new DefaultScanStrategy();
        ScanModel scanModel = scanStrategy.scan(Arrays.asList("com.coder.dream"));
        System.out.println(scanModel);
    }

    public void test2(){
        DefaultScanStrategy scanStrategy = new DefaultScanStrategy();
        ScanModel scanModel = scanStrategy.scan(Arrays.asList("com.coder.dream"));
        System.out.println(scanModel);
        TestConsumerModule testConsumerModule = new TestConsumerModule(scanModel);
        Injector injector = Guice.createInjector(testConsumerModule);
        ConsumerInitializer initializer = injector.getInstance(ConsumerInitializer.class);
        initializer.start();
        System.out.println("正在等候");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        initializer.stop();
    }
}
