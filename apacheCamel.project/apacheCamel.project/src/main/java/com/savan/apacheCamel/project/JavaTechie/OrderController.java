package com.savan.apacheCamel.project.JavaTechie;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allOrder")
    public List<Order> getAll(){
        return orderService.getOrder();
    }

    @PostMapping("/add")
    public String post(@RequestBody Order order){
        orderService.addOrder(order);
        return "Added ";
    }
}
