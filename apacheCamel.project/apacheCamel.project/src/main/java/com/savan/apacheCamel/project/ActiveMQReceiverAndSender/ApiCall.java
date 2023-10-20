package com.savan.apacheCamel.project.ActiveMQReceiverAndSender;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

//@Component
class ApiCall extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("activemq:test-mq?requestTimeout=60000&timeToLive=60000")
                .to("direct:callAPI");

        from("direct:callAPI")
                .setProperty("Counter", constant(0))
                .loop(3)
                    .doTry()
                        .setHeader(Exchange.HTTP_METHOD, simple("GET"))
                        .to("http://localhost:8082/api/car?bridgeEndpoint=true")
                        .log("Received a response from REST API: ${body}")
                    .doCatch(Exception.class)
                        .log("Retrying in 15 sec ....")
                        .process(exchange -> {
                            Thread.sleep(15000);
                        })
                    .doFinally()
                        .choice()
                            .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(200))
                                .stop()
                            .when(exchangeProperty("Counter").isEqualTo(2))
                                .log("Try after some time ... ")
                            .otherwise()
                                .process(exchange -> {
                                    exchange.setProperty("Counter", exchange.getProperty("Counter", Integer.class) + 1);
                                })
                .end();

    }
}