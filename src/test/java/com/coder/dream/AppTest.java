package com.coder.dream;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.coder.dream.alibaba.ons.config.MqConfig;
import com.coder.dream.alibaba.ons.producer.ProducerConfig;
import com.coder.dream.alibaba.ons.producer.ProducerInitializer;
import com.coder.dream.alibaba.ons.producer.ProducerService;
import com.coder.dream.test.TestProducerConfigurer;
import com.coder.dream.test.TestProducerModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Date;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        ProducerConfig config = new ProducerConfig(){
            @Override
            public Properties getProperties() {
                Properties properties = new Properties();
                properties.setProperty(PropertyKeyConst.ProducerId, MqConfig.PRODUCER_ID);
                properties.setProperty(PropertyKeyConst.AccessKey, MqConfig.ACCESS_KEY);
                properties.setProperty(PropertyKeyConst.SecretKey, MqConfig.SECRET_KEY);
                properties.setProperty(PropertyKeyConst.ONSAddr, "http://onsaddr-internet.aliyun.com/rocketmq/nsaddr4client-internet");
                return properties;
            }
        };
        TestProducerModule module = new TestProducerModule(config);
        Injector injector = Guice.createInjector(module);

        ProducerInitializer initializer = injector.getInstance(ProducerInitializer.class);
        initializer.start();
        ProducerService producerService = injector.getInstance(ProducerService.class);
        for (int i = 0; i < 10; i++) {
            Message message = new Message(MqConfig.TOPIC, MqConfig.TAG, new String("mq send message test" + i).getBytes());
            SendResult sendResult = producerService.send(TestProducerConfigurer.TRADE_CREATE_EVENT, message);
            if (sendResult != null) {
                System.out.println(new Date() + " Send mq message success! Topic is:" + MqConfig.TOPIC + "msgId is: " + sendResult.getMessageId());
            }
        }
        initializer.stop();
    }
}
