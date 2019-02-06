package com.demo.exchange.data.model;

public class CurrencyChange {

    private String from;
    private String to;
    private Double amount;

    public CurrencyChange() {
    }

    public CurrencyChange(String from, String to, Double amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CurrencyChange [from=" + from + ", to=" + to + ", amount=" + amount + "]";
    }

}
