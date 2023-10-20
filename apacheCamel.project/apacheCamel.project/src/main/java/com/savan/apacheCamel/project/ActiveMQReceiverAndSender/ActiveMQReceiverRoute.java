package com.savan.apacheCamel.project.ActiveMQReceiverAndSender;

import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQReceiverRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        from("activemq:test-mq")
//                .log("log:receiving messages on activeMQ, using Rest API Calling .... => ${body}")

//        from("activemq:test-mq")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8082/api/car?bridgeEndpoint=true")
//                .log("Response from REST API: ${body}");


        // Trigger the API call with retries
        from("timer:apiTimer?period=10000")  // Trigger every 10 seconds
                .to("direct:callAPI");


//        from("activemq:test-mq")
//                .log("Calling API ... ")
//                .to("direct:to-api");

//        from("direct:to-api")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8082/api/car?bridgeEndpoint=true")
//                .onException(Exception.class)
//                .choice()
//                .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(200))
//                .log("Response from REST API: ${body}")
//                .otherwise()
//                .redeliveryDelay(5000)
//                .maximumRedeliveries(-1)
//                .log("Retrying to hit the URL http://localhost:8082/api/car...")
//                .continued((Predicate) simple("${response} != null"))
//                .log("Response from REST API: ${body}");



//        onException(Exception.class)
//                .log(LoggingLevel.ERROR, "Exception caught: ${exception.message}\nRetrying in 5 seconds...")
//                .redeliveryDelay(5000)
//                .maximumRedeliveries(5)
//                .handled(true)
//                .to("direct:mq");
//
//        from("direct:mq")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8081/camel/restproducer?bridgeEndpoint=true")
//                .choice()
//                .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(200))
//                .log("Received a response from REST API: ${body}")
//                .otherwise()
//                .log("Response from REST API: ${body}")
//                .to("direct:retry");
//
//        from("activemq:test-mq")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8082/api/car?bridgeEndpoint=true")
//                .log("Response from REST API: ${body}");
//
//        from("direct:retry")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8082/api/car?bridgeEndpoint=true")
//                .choice()
//                .when(header(Exchange.HTTP_RESPONSE_CODE).isEqualTo(200))
//                .log("Received a response from REST API: ${body}")
//                .otherwise()
//                .to("direct:mq");

//        from("activemq:test-mq")
//                .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//                .to("http://localhost:8082/api/car?bridgeEndpoint=true")
//                .log("Response from REST API: ${body}")
//                .onException(Exception.class)
//                .redeliveryDelay(5000)
//                .maximumRedeliveries(-1)
//                .log("Retrying to hit the URL http://localhost:8082/api/car...")
//                .continued((Predicate) simple("${response} != null"))
//                .log("Response from REST API: ${body}");

    }
}
