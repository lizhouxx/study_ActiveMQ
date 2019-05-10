package com.z.a;

import javax.jms.Queue;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestMeController {
    
    @Autowired
    private JmsTemplate jmsTemplate;

//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
    
    @Autowired
    private Queue queue;
    @Autowired
    private Topic topic;
    
    private static int count= 0;

    
    @GetMapping(value = "/t")
    public String t() {
        count++;
        this.jmsTemplate.convertAndSend(this.queue,"hi.activeMQ,index="+count);
        this.jmsTemplate.convertAndSend(this.topic,"hi,activeMQ( topic )，index="+count);

        return "OK "+count;
    }
}
