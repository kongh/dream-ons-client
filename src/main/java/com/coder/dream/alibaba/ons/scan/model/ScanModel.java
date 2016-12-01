package com.coder.dream.alibaba.ons.scan.model;

import com.coder.dream.alibaba.ons.config.SubscriptionConfig;

import java.util.Map;
import java.util.Set;

/**
 * Created by konghang on 2016/11/15.
 */
public class ScanModel {

    private Map<String, Set<SubscriptionConfig>> subscriptions;

    public Map<String, Set<SubscriptionConfig>> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Map<String, Set<SubscriptionConfig>> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
