package com.savan.apacheCamel.project;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class EIPSplitRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest("/names")
                .post()
                .to("direct:names-list");

        from("direct:names-list")
                .transform().body(String.class)
                .split(body()).delimiter(",") // Try commenting it, then see the output
                .to("direct:print-name");

        from("direct:print-name")
                .to("log:print-name");
    }
}

// http://localhost:8081/camel/names