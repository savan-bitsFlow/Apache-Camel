package com.savan.apacheCamel.project;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class EIPChoiceRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("/api")
                .post()
                .to("direct:choice-testing");

        from("direct:choice-testing")
                .choice()
                    .when(body().contains("Hello World")).to("direct:hello-world")
                    .when(simple("${body} contains 'apache camel'")).to("direct:apache-camel")
                    .when(header("developer").isEqualTo("java")).to("direct:java-route")
                .otherwise()
                .to("direct:dummy-route")
                .end();

        from("direct:hello-world")
                .log("Hello World Route!");

        from("direct:apache-camel")
                .log("apache camel Route!");

        from("direct:java-route")
                .log("java Route!");

        from("direct:dummy-route")
                .log("dummy Route!");

    }
}

// http://localhost:8081/camel/api