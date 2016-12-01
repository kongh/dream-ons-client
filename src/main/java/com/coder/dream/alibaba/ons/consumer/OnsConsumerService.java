package com.coder.dream.alibaba.ons.consumer;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.coder.dream.alibaba.ons.config.SubscriptionConfig;
import com.coder.dream.alibaba.ons.scan.ScanManager;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * Created by konghang on 2016/12/1.
 */
@Singleton
public class OnsConsumerService implements ConsumerService{

    private static final String defaultScanPacakges = "com";

    private String scanPackages = defaultScanPacakges;

    @Inject
    private ScanManager scanManager;

    @Inject
    private Map<String, ConsumerProvider> providers;

    @Inject
    private Injector injector;

    @Override
    public Action consume(Message message, ConsumeContext context) {
        if( providers != null && !providers.isEmpty()) {
            for(ConsumerProvider provider : providers.values()) {
                Set<SubscriptionConfig> subscriptions = provider.getSubscriptions();
                for(SubscriptionConfig config : subscriptions){
                    try {
                        Object instance = injector.getInstance(config.getClazz());
                        Set<Method> methods = config.getMethods();
                        for(Method method : methods){
                            try {
                                method.invoke(instance, message, context);
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }  catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void start() {
        String[] scanPackages = this.scanPackages.split(",");
        ScanModel scanModel = scanManager.scanAnnotations(Arrays.asList(scanPackages));
        if( providers != null && !providers.isEmpty()) {
            for(ConsumerProvider provider : providers.values()) {
                provider.init(this);
                provider.get().start();
            }
        }
    }

    @Override
    public void stop() {
        if(providers != null && !providers.isEmpty()) {
            for(ConsumerProvider provider : providers.values()) {
                provider.get().shutdown();
            }
        }
    }
}
