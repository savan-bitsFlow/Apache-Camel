package com.savan.apacheCamel.project;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class BeanTestRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer:bean-test-timer?period=5000")
                .bean("calculator", "add")
                .to("log:bean test timer");
    }
}

//@Component
class Calculator{
    public String add(){
        return "This is add method ....";
    }

    public String sub(){
        return "This is sub method ....";
    }
}
