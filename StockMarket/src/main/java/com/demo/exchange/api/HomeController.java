package com.demo.exchange.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public String showHome(){
        return "Welcome to the new generation of foreign currency conversion! :)";
    }
    
}
