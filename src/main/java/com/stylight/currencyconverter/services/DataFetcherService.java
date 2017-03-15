package com.stylight.currencyconverter.services;


import com.stylight.currencyconverter.dto.ConversionRateDTO;

/**
 * Created by prasadus on 08/03/17.
 */
public interface DataFetcherService {
    ConversionRateDTO getConversionRate(String sourceCurrency, String destinationCurrency);
}
