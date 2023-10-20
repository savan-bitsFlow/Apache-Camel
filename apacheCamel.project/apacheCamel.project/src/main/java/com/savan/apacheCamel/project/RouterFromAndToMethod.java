package com.savan.apacheCamel.project;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RouterFromAndToMethod extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("timer://test?period=5000")
//                .setBody(simple("Welcome to apache camel routing ......"))
//                .to("log:test");

        from("servlet://sawan")
                .setBody(simple("Welcome to apache camel routing ......"))
                .to("log:test");

        // http://localhost:8081/camel/sawan
    }
}
