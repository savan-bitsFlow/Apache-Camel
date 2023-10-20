package com.savan.apacheCamel.project;

// Move file from one directory to another directory

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.StringTokenizer;

@Component
public class FileTestingRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        System.out.println("Started ... ");
//        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
//                .log("${body}")
//                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output");
//
//        // Or from Here
//        sendAllFiles();

        // Send Specific file
//        sendSpecificFile("myfile");

        // Send Specific file starts with specific content
//        sendSpecificFileWithBody("java");

        // Receive file do processing then Send
        conversion();

        // CSV to JSON
//        csvToCamel();

        System.out.println("End ...");

    }

    public void sendAllFiles(){
        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
                .log("${body}")
                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output");
    }

    public void sendSpecificFile(String type){
        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
                .filter(header(Exchange.FILE_NAME).startsWith(type))
                .log("${body}")
                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output");
    }

    public void sendSpecificFileWithBody(String content){
        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
                .filter(body().startsWith(content))
                .log("${body}")
                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output");
    }

    public void conversion(){
        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
                .log("${body}")
                .process(exchange -> {
                    Message input = exchange.getIn();

                    String data = input.getBody(String.class);

                    StringTokenizer str = new StringTokenizer(data, ",");

//                    String eid = str.nextToken();
//                    String ename = str.nextToken();
//                    String esal = str.nextToken();
//                    String dataModified = "{\"eid\":" + eid + ",\"ename\":\"" + ename + "\",\"esal\":" + esal + "}";

                    String carName = str.nextToken();
                    String company = str.nextToken();
                    String carModel = str.nextToken();
                    String dataModified = "{\"carName\":\"" + carName + "\",\"company\":\"" + company + "\",\"carModel\":\"" + carModel + "\"}";

                    Message output = exchange.getMessage();

                    output.setBody(dataModified);
                })
                .log("${body}")
                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output?fileName=car.json")
                .to("activemq:test-mq");

        // Goto CarApplication for this
//        from("activemq:test-mq")
//                .log("Message from avtivemq => ${body}");
    }

    public void csvToCamel(){
        from("file://C:\\Users\\admin\\Desktop\\FileRouter\\Input?noop=true")
                .unmarshal().csv()
                .marshal().json()
                .to("file://C:\\Users\\admin\\Desktop\\FileRouter\\Output?fileName=cars.json")
                .to("activemq:test-mq");
    }
}
