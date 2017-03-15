package com.stylight.currencyconverter.services;

import com.stylight.currencyconverter.dto.ConversionDataDTO;

/**
 * Created by prasadus on 08/03/17.
 */
public interface CurrencyConversionService {
    ConversionDataDTO getConvertedValue(String sourceCurrency, String destinationCurrency, Double sourceCurrencyValue);
}
