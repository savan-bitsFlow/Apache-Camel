package com.savan.apacheCamel.project;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class EIPMulticastPipelineRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("payment")
                .get()
                        .to("direct:payment-completed");


        from("direct:payment-completed")
                .setBody().constant("Payment Completed for your Purchase ....")
                .multicast()
                .parallelProcessing()
                .to("direct:sender-bank-system")
                .to("direct:receiver-bank-system")
                .to("direct:online-shopping-system");

        from("direct:sender-bank-system")
                .log("${body} ... ${threadName}");

        from("direct:receiver-bank-system")
                .log("${body} ... ${threadName}");

        from("direct:online-shopping-system")
                .log("${body} ... ${threadName}");
    }
}


// http://localhost:8081/camel/payment