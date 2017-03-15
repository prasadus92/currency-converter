package com.stylight.currencyconverter.services.impl;

import com.stylight.currencyconverter.dto.ConversionDataDTO;
import com.stylight.currencyconverter.dto.ConversionRateDTO;
import com.stylight.currencyconverter.entities.enums.CURRENCY_TYPE;
import com.stylight.currencyconverter.services.CurrencyConversionService;
import com.stylight.currencyconverter.services.DataFetcherService;
import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by prasadus on 09/03/17.
 */
@Service
public class CurrencyConversionServiceImpl implements CurrencyConversionService {

    @Autowired
    DataFetcherService dataFetcherService;

    private boolean isValidCurrencyType(String currencyType) {
        return EnumUtils.isValidEnum(CURRENCY_TYPE.class, currencyType);
    }

    public ConversionDataDTO getConvertedValue(String fromCurrency,
                                               String toCurrency,
                                               Double fromCurrencyValue) throws RuntimeException {
        if (!isValidCurrencyType(fromCurrency) || !isValidCurrencyType(toCurrency)) {
            throw new IllegalArgumentException("Invalid currency type passed.");
        }
        ConversionDataDTO conversionDataDTO = new ConversionDataDTO();
        conversionDataDTO.setSourceCurrency(fromCurrency);
        conversionDataDTO.setTargetCurrency(toCurrency);
        conversionDataDTO.setSourceCurrencyValue(fromCurrencyValue);
        ConversionRateDTO conversionRateDTO = dataFetcherService.getConversionRate(fromCurrency, toCurrency);
        if (conversionRateDTO == null) {
            throw new RuntimeException("Failed to fetch conversion rate.");
        }
        Double targetValue = fromCurrencyValue * conversionRateDTO.getConversionRate();
        conversionDataDTO.setTargetCurrencyValue(targetValue);
        return conversionDataDTO;
    }
}
