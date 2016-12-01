package com.coder.dream.alibaba.ons.scan.impl;


import com.coder.dream.alibaba.ons.scan.ScanManager;
import com.coder.dream.alibaba.ons.scan.model.ScanModel;
import com.coder.dream.alibaba.ons.scan.strategy.ScanStrategy;
import com.coder.dream.alibaba.ons.scan.strategy.impl.DefaultScanStrategy;

import java.util.List;

/**
 * Created by konghang on 2016/11/15.
 */
public class DefaultScanManager implements ScanManager {

    private ScanStrategy scanStrategy = new DefaultScanStrategy();

    @Override
    public ScanModel scanAnnotations(List<String> packageNameLit) {
        return scanStrategy.scan(packageNameLit);
    }
}
