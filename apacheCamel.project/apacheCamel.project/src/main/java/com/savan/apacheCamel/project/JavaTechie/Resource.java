package com.savan.apacheCamel.project.JavaTechie;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

//@Component
public class Resource extends RouteBuilder {

    @Autowired
    private OrderService orderService;

    @Override
    public void configure() throws Exception {
//        restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);
//
//        rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE)
//                .setBody().constant("Payment Completed for your Purchase ....");
//
//        rest().get("/hello-world")
//                .produces(MediaType.APPLICATION_JSON_VALUE)
//
//                .setBody(constant("Welcome here ... "));
//
//        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE)

        from("servlet://getAll").to("http://localhost:8081/allOrder?bridgeEndpoint=true");

        from("servlet://add").to("http://localhost:8081/add?bridgeEndpoint=true");

        from("servlet://car").to("http://localhost:8082/api/car?bridgeEndpoint=true");


    }
}
