package com.coder.dream.alibaba.ons.scan.strategy;


import com.coder.dream.alibaba.ons.scan.model.ScanModel;

import java.util.List;

/**
 * 扫描策略
 *
 * Created by konghang on 2016/11/15.
 */
public interface ScanStrategy {

    /**
     * 扫描
     *
     * @param packageList
     * @return
     */
    ScanModel scan(List<String> packageList);
}
