package com.demo.exchange.api;

import com.demo.exchange.api.services.CurrencyService;
import com.demo.exchange.data.model.Currency;
import com.demo.exchange.data.model.CurrencyChange;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("market")
public class MarketController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/all")
    public List<Currency> getAllCurrency() {
        return currencyService.getAllCurrency();
    }

    @GetMapping("/find/{id}")
    public Currency getCurrencyById(@PathVariable Integer id) {
        Currency curr = currencyService.getCurrencyById(id);

        return curr;
    }
    
    @GetMapping("/exchange/{from}/{to}/{amount}")
    public Currency exchangeCurrency(@PathVariable String from,
            @PathVariable String to,
            @PathVariable Double amount){
        
        return currencyService.calculate(new CurrencyChange(from, to, amount));
    }
}
