package com.savan.apacheCamel.project.JavaTechie;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private List<Order> orders = new ArrayList<>();

    @PostConstruct
    public void initDB(){
        orders.add(new Order(12, "Mobile", 50000));
        orders.add(new Order(45, "Book", 1200));
        orders.add(new Order(15, "AC", 50000));
        orders.add(new Order(4, "PC", 8200));
        orders.add(new Order(32, "Cloth", 500));
        orders.add(new Order(46, "Shoe", 2000));
    }

    public Order addOrder(Order order){
        orders.add(order);
        return order;
    }

    public List<Order> getOrder(){
        return orders;
    }
}
