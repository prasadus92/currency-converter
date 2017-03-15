package com.stylight.currencyconverter.services.impl.utils.datafetcher;

/**
 * Created by prasadus on 09/03/17.
 */
public interface DataFetcher {
    Double getConversionRate(String sourceCurrency, String destinationCurrency);
}
