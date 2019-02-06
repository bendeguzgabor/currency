package com.demo.exchange.api.services;

import com.demo.exchange.data.mapper.CurrencyMapper;
import com.demo.exchange.data.model.Currency;
import com.demo.exchange.data.model.CurrencyChange;
import com.demo.exchange.util.Rounder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyMapper currencyMapper;

    public List<Currency> getAllCurrency() {
        return currencyMapper.findAll();
    }

    public Currency getCurrencyById(Integer id) {
        Optional<Currency> curr = currencyMapper.findById(id);

        if (!curr.isPresent()) {
            throw new NullPointerException("Can not find any currency with id[" + id + "]");
        }
        return curr.get();
    }

    public Currency calculate(CurrencyChange currencyChange) {
        Currency from = new Currency();
        Currency to = new Currency();
        
        //get all currencies from db
        List<Currency> allCurr = currencyMapper.findAll();

        //select the two currencies for exchange
        List<Currency> result = allCurr.stream()
                .filter(c -> c.getCurrencyName().equals(currencyChange.getFrom()) || c.getCurrencyName().equals(currencyChange.getTo())
                ).collect(Collectors.toList());

        //configure 'from' and 'to' currencies
        for (Currency currency : result) {
            if (currency.getCurrencyName().equals(currencyChange.getFrom())) {
                from = currency;
            } else {
                to = currency;
            }
        }

        return getCalculatedCurrency(from, to, currencyChange);
    }

    private Currency getCalculatedCurrency(Currency from, Currency to, CurrencyChange currencyChange) {

        //calculate currencies
        Double preSaleValue = from.getSale() * currencyChange.getAmount();
        Double saleResult = preSaleValue / to.getSale();

        Double preBuyValue = from.getBuy() * currencyChange.getAmount();
        Double buyResult = preBuyValue / to.getBuy();

        String joinedCurrencyNames = from.getCurrencyName() + "->" + to.getCurrencyName();

        //return object with formatted double values...
        return new Currency(null, joinedCurrencyNames, Rounder.round(saleResult, 2), Rounder.round(buyResult, 2));
    }

}
