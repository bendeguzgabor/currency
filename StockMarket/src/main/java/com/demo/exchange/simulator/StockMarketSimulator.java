package com.demo.exchange.simulator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class StockMarketSimulator implements CommandLineRunner{
    
    @Autowired
    private StockMarketSimulatorService marketService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Stock Market Simulator - START");
        marketService.startSimulating();
    }
    
    
}
