package com.stylight.currencyconverter.controllers;

import com.stylight.currencyconverter.dto.ConversionDataDTO;
import com.stylight.currencyconverter.dto.ConversionRateDTO;
import com.stylight.currencyconverter.services.CurrencyConversionService;
import com.stylight.currencyconverter.services.DataFetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by prasadus on 08/03/17.
 */
@Controller
public class RestController {

    @Autowired
    CurrencyConversionService currencyConversionService;

    @Autowired
    DataFetcherService dataFetcherService;

    @RequestMapping("/api/v1/currency/getConversionRate")
    @ResponseBody
    public ConversionRateDTO getConversionRate(@RequestParam String sourceCurrency,
                                               @RequestParam String destinationCurrency) {
        return dataFetcherService.getConversionRate(sourceCurrency, destinationCurrency);
    }

    @RequestMapping("/api/v1/currency/getConvertedValue")
    @ResponseBody
    public ConversionDataDTO getConvertedValue(@RequestParam String sourceCurrency,
                                               @RequestParam String destinationCurrency,
                                               @RequestParam Double sourceCurrencyValue) {
        return currencyConversionService.getConvertedValue(sourceCurrency, destinationCurrency, sourceCurrencyValue);
    }
}
