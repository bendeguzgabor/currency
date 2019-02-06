package com.demo.exchange.data.model;

public class Currency {

    private Integer currencyId;
    private String currencyName;
    private Double sale;
    private Double buy;

    public Currency() {
    }

    public Currency(Integer currencyId, String currencyName, Double sale, Double buy) {
        this.currencyId = currencyId;
        this.currencyName = currencyName;
        this.sale = sale;
        this.buy = buy;
    }

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getSale() {
        return sale;
    }

    public void setSale(Double sale) {
        this.sale = sale;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    @Override
    public String toString() {
        return "Currency [currencyId=" + currencyId + ", currencyName=" + currencyName + ", sale=" + sale + ", buy="
                + buy + "]";
    }

}
