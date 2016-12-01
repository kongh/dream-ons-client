package com.coder.dream.alibaba.ons.producer;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendCallback;
import com.aliyun.openservices.ons.api.SendResult;

/**
 * Created by konghang on 2016/12/1.
 */
public interface ProducerService {

    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();

    /**
     * 同步发送消息，只要不抛异常就表示成功
     *
     * @param message
     * @return 发送结果，含消息Id
     */
    SendResult send(final ProducerRegister register, final Message message);


    /**
     * 发送消息，Oneway形式，服务器不应答，无法保证消息是否成功到达服务器
     *
     * @param message
     */
    void sendOneway(final ProducerRegister register, final Message message);

    /**
     * 发送消息，异步Callback形式
     *
     * @param message
     */
    void sendAsync(final ProducerRegister register, final Message message, final SendCallback sendCallback);
}
