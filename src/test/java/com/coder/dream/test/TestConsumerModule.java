package com.coder.dream.test;

import com.coder.dream.alibaba.ons.consumer.DefaultConsumerModule;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;

/**
 * Created by konghang on 2016/12/2.
 */
public class TestConsumerModule extends DefaultConsumerModule {

    public TestConsumerModule(ScanModel scanModel) {
        super(scanModel);
    }
}
