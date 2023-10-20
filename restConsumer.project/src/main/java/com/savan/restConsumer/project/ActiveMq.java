package com.savan.restConsumer.project;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class ActiveMq extends RouteBuilder {

    @Autowired
    private Service service;

    @Override
    public void configure() throws Exception {
        from("activemq:test-mq")
                .log("Message from avtivemq => ${body}")
                .unmarshal().json(JsonLibrary.Jackson, Car.class)
                .log("After Unmarshal => ${body}")
                .to("bean:service?method=add")
                .marshal().json()
                .log("After Marshal => ${body}")

                .log("Rest API Calling ....")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:8082/api/car/0")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String output = exchange.getIn().getBody(String.class);
                        System.out.println("Output is: "+output);
                    }
                })

                .to("http://localhost:8082/api/car/all")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String output = exchange.getIn().getBody(String.class);
                        System.out.println("All Cars: "+output);
                    }
                });
    }
}
