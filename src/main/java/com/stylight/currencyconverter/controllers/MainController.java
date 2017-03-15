package com.stylight.currencyconverter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by prasadus on 06/03/17.
 */
@Controller
public class MainController {

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Stylight Currency Converter Service is up and running.\n" +
                "Supported APIs:\n" +
                "/api/v1/currency/getConversionRate?sourceCurrency={}&destinationCurrency={}\n" +
                "/api/v1/currency/getConvertedValue?sourceCurrency={}&destinationCurrency={}&sourceCurrencyValue={}";
    }
}
