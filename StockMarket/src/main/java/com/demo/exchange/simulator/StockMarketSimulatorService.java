package com.demo.exchange.simulator;

import com.demo.exchange.data.mapper.CurrencyMapper;
import com.demo.exchange.data.model.Currency;
import com.demo.exchange.util.Rounder;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockMarketSimulatorService {

    @Autowired
    private CurrencyMapper currencyMapper;

    public void startSimulating() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            //select all currency
            List<Currency> allCurr = currencyMapper.findAll();
            //changing values of buy and sale randomly
            List<Currency> manipulated = manipulateRows(allCurr);
            //update them
            manipulated.forEach(m -> currencyMapper.update(m));

        }, 1, 30, TimeUnit.SECONDS);
    }

    private List<Currency> manipulateRows(List<Currency> allCurr) {
        allCurr.forEach(c -> {
            Random rand = new Random();
            double coin = Math.random();
            double manipulateBuy = rand.nextInt(501) / 100;
            double manipulateSale = rand.nextInt(301) / 100;

            //sub or add manipulated buy/sale depends on random double + round them
            if (coin < 0.5) {
                c.setBuy(Rounder.round(c.getBuy() + manipulateBuy, 2));
                c.setSale(Rounder.round(c.getSale() + manipulateSale, 2));
            } else {
                c.setBuy(Rounder.round(c.getBuy() - manipulateBuy, 2));
                c.setSale(Rounder.round(c.getSale() - manipulateSale, 2));
            }
            
            //Sale can not be more than buy..that's how business works..
            if(c.getSale() > c.getBuy()){ 
                double incr = Rounder.round((c.getSale() - c.getBuy()) + 5, 2);
                c.setBuy(c.getBuy() + incr);
            }
        });
        
        return allCurr;
    }
}
