package com.demo.exchange.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rounder {

    //method to round double values (formatting like #*.##)
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        return new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue();
        
    }
}
