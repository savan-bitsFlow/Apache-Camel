package com.savan.restConsumer.project;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
public class Service {

    List<Car> cars = new ArrayList<>();

    public String add(Car car){
        cars.add(car);
        System.out.println(car);
        return "Added Successfully!";
    }

    public Car get(int index){
        return cars.get(index);
    }

    public List<Car> getAll(){
        return cars;
    }
}
