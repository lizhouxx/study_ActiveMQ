package com.z.a;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
public class Consumerqueue {
    @JmsListener(destination = "mvp.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic(String text){
        System.out.println("Topic Consumer1:"+text);
    }
    @JmsListener(destination = "mvp.topic",containerFactory="jmsListenerContainerTopic")
    public void receiveTopic2(String text){
        System.out.println("Topic Consumer2:"+text);
    }
    
    @JmsListener(destination = "mvp.queue"/*,containerFactory="jmsListenerContainerQueue"*/)
    @SendTo("out.queue")
    public String reviceQueue(String text){
        System.out.println("Queue Consumer1:"+text);
        return text;//@SendTo 所以要有返回
    }
    @JmsListener(destination = "mvp.queue"/*,containerFactory="jmsListenerContainerQueue"*/)
    public void reviceQueue2(String text){
        System.out.println("Queue Consumer2:"+text);
    }
    
    @JmsListener(destination = "out.queue")
    public void consumerMessage(String text) {
        System.out.println("----Consumer-out : 回复报文:" + text);
    }
    
    
}