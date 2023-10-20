package com.savan.restConsumer.project;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarDetailsController {

    @Autowired
    private Service service;

    @PostMapping("")
    public String addDetails(Car car){
        return service.add(car);
    }
    @GetMapping("/{index}")
    public Car getDetails(@PathVariable("index") int index){
        return service.get(index);
    }

    @GetMapping("/all")
    public List<Car> all(){
        return service.getAll();
    }


}
