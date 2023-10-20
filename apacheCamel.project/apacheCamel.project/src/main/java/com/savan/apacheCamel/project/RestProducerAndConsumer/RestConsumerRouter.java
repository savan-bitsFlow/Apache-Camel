package com.savan.apacheCamel.project.RestProducerAndConsumer;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

//@Component
public class RestConsumerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://test-rest-api?period=10000")
                .log("Rest API Calling ....")
                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                .to("http://localhost:8082/api/car")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        String output = exchange.getIn().getBody(String.class);
                        System.out.println("Output is: "+output);
                    }
                });
    }
}
