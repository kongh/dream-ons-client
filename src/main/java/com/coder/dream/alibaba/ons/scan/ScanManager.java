package com.coder.dream.alibaba.ons.scan;


import com.coder.dream.alibaba.ons.scan.model.ScanModel;

import java.util.List;

/**
 * 注解扫描管理器
 *
 * Created by konghang on 2016/11/15.
 */
public interface ScanManager {

    /**
     * 扫描注解配置
     *
     * @param packageNameLit
     */
    ScanModel scanAnnotations(List<String> packageNameLit);

}
