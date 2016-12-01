package com.coder.dream.alibaba.ons.test;

import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.coder.dream.alibaba.ons.annotation.CID;
import com.coder.dream.alibaba.ons.annotation.Subscription;
import com.coder.dream.alibaba.ons.config.MqConfig;

/**
 * Created by konghang on 2016/12/1.
 */
@CID(value = MqConfig.CONSUMER_ID)
public class TestSubscribe {

    /**
     * 订阅KH-Test-topic下所有消息
     *
     * @param message
     * @param consumeContext
     * @return
     */
    @Subscription(topic = MqConfig.TOPIC)
    public Action consumeA(Message message, ConsumeContext consumeContext) {
        System.out.println(message.getMsgID() + "consumerA");
        return Action.CommitMessage;
    }

    @Subscription(topic = "12345")
    public Action consumeB(Message message, ConsumeContext consumeContext) {
        System.out.println(message.getMsgID() + "consumerB");
        return Action.CommitMessage;
    }
}
