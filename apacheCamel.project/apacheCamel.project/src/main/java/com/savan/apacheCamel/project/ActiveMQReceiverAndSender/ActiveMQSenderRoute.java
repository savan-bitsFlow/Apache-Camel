package com.savan.apacheCamel.project.ActiveMQReceiverAndSender;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQSenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("timer://test?period=10000")
//                .setBody(simple("Welcome to active mq ...."))
//                .to("activemq:test-mq");


        restConfiguration().component("servlet");

        rest("/activeMQProducer")
                .get()
                .to("activemq:test-mq");
    }
}
