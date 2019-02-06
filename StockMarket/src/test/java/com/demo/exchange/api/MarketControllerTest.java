package com.demo.exchange.api;

import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.demo.exchange.api.services.CurrencyService;
import com.demo.exchange.data.model.Currency;

@RunWith(SpringRunner.class)
@AutoConfigureMybatis
@WebMvcTest(MarketController.class)
public class MarketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService service;

    @Test
    public void test_getAllCurrency() throws Exception {

        Currency currency1 = new Currency(1, "HUF", 100.30, 150.40);
        Currency currency2 = new Currency(2, "USD", 102.34, 142.21);

        List<Currency> allCurr = Arrays.asList(currency1, currency2);

        given(service.getAllCurrency()).willReturn(allCurr);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/market/all")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyId", Matchers.is(currency1.getCurrencyId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].currencyName", Matchers.is(currency1.getCurrencyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sale", Matchers.is(currency1.getSale())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].buy", Matchers.is(currency1.getBuy())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currencyId", Matchers.is(currency2.getCurrencyId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].currencyName", Matchers.is(currency2.getCurrencyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].sale", Matchers.is(currency2.getSale())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].buy", Matchers.is(currency2.getBuy())));
    }

    @Test
    public void test_getCurrencyByName() throws Exception {

        Currency currency = new Currency(1, "USD", 274.17, 257.71);

        given(service.getCurrencyById(1)).willReturn(currency);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/market/find/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyId", Matchers.is(currency.getCurrencyId())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyName", Matchers.is(currency.getCurrencyName())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sale", Matchers.is(currency.getSale())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.buy", Matchers.is(currency.getBuy())));
    }

//    @Test
//    public void test_exchangeCurrency() throws Exception {
//        
//        CurrencyChange currChange = new CurrencyChange("USD", "EUR", 150.00);
//        Currency currency = new Currency(null, "USD->EUR", 116.64, 126.10);
//
//        given(service.calculate(currChange)).willReturn(currency);
//
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/market/exchange/USD/EUR/150")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyId").doesNotExist())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.currencyName", Matchers.is(currency.getCurrencyName())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.sale", Matchers.is(currency.getSale())))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.buy", Matchers.is(currency.getBuy())));
//    }
}
