package com.coder.dream.alibaba.ons.scan.strategy.impl;

import com.coder.dream.alibaba.ons.annotation.CID;
import com.coder.dream.alibaba.ons.annotation.Subscription;
import com.coder.dream.alibaba.ons.config.SubscriptionConfig;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;
import com.coder.dream.alibaba.ons.scan.strategy.ScanStrategy;
import com.google.common.base.Predicate;
import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * Created by konghang on 2016/11/15.
 */
public class DefaultScanStrategy implements ScanStrategy {

    @Override
    public ScanModel scan(List<String> packageList) {
        ScanModel scanModel = scanBasicInfo(packageList);
        return scanModel;
    }

    private ScanModel scanBasicInfo(List<String> packNameList) {

        ScanModel scanModel = new ScanModel();

        //扫描handler方法
        Reflections reflections = getReflection(packNameList);
        Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(CID.class);

        //转化配置关系
        Map<String, Set<SubscriptionConfig>> subscriptions = convertSubscriptionConfig(reflections, annotatedClasses);
        scanModel.setSubscriptions(subscriptions);

        //验证
        validMethods(null);

        //解析配置
        return scanModel;
    }

    private void validMethods(Set<Method> methods){

    }

    private Map<String, Set<SubscriptionConfig>> convertSubscriptionConfig(Reflections reflections, Set<Class<?>> annotatedClasses) {
        Map<String, Set<SubscriptionConfig>> configMap = new HashMap<>();
        for(Class<?> clazz : annotatedClasses){
            SubscriptionConfig config = convertSubscriptionConfig(reflections, clazz);
            if(config == null){
                continue;
            }
            Set<SubscriptionConfig> subscriptionConfigs = configMap.get(config.getCid());
            if(subscriptionConfigs == null){
                subscriptionConfigs = new HashSet<>();
                configMap.put(config.getCid(), subscriptionConfigs);
            }
            subscriptionConfigs.add(config);
        }
        return configMap;
    }

    private SubscriptionConfig convertSubscriptionConfig(Reflections reflections, Class<?> clazz) {
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(Subscription.class);
        if(annotatedMethods.size() == 0){
            return null;
        }

        SubscriptionConfig config = new SubscriptionConfig();
        config.setCid(clazz.getDeclaredAnnotation(CID.class).value());
        config.setClazz(clazz);
        config.setMethods(annotatedMethods);
        //TODO:校验重复
        return config;
    }

    /**
     * 通过扫描，获取反射对象
     */
    private Reflections getReflection(List<String> packNameList) {

        //
        // filter
        //
        FilterBuilder filterBuilder = new FilterBuilder();

        for (String packName : packNameList) {
            filterBuilder = filterBuilder.includePackage(packName);
        }
        Predicate<String> filter = filterBuilder;

        //
        // urls
        //
        Collection<URL> urlTotals = new ArrayList<URL>();
        for (String packName : packNameList) {
            Set<URL> urls = ClasspathHelper.forPackage(packName);
            urlTotals.addAll(urls);
        }

        //
        Reflections reflections = new Reflections(new ConfigurationBuilder().filterInputsBy(filter)
                .setScanners(new SubTypesScanner().filterResultsBy(filter),
                        new TypeAnnotationsScanner()
                                .filterResultsBy(filter),
                        new MethodAnnotationsScanner()
                                .filterResultsBy(filter)).setUrls(urlTotals));

        return reflections;
    }
}
