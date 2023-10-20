package com.savan.apacheCamel.project.RestProducerAndConsumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestProducerRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("/restproducer")
                .get()
                .to("direct:rest-producer-camel");

        from("direct:rest-producer-camel")
                .transform().constant("Welcome to apache camel rest-producer");
    }
}

// http://localhost:8081/camel/restproducer
