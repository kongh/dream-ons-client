package com.coder.dream.alibaba.ons.producer;

/**
 * 生产者注册
 *
 * PID:一个发布应用的标识—发布组，我们建议一个应用一个PID即可（从应用常量中获取同一个值）。
 *
 * 但是，为了兼容我们之前的系统，按模块划分的发布组别，所有增加了发布组的配置
 * 当你配置了PID，我们会根据PID为你创建一个对应的producer
 * Created by konghang on 2016/12/1.
 */
public interface ProducerRegister {

    /**
     * 发布组
     *
     * @return
     */
    String getPid();

    /**
     * 主题
     *
     * @return
     */
    String getTopic();

    /**
     * 标签
     *
     * @return
     */
    String getTag();
}
