package com.stylight.currencyconverter.services.impl;

import com.stylight.currencyconverter.dto.ConversionRateDTO;
import com.stylight.currencyconverter.entities.enums.DATAFETCHER_TYPE;
import com.stylight.currencyconverter.services.DataFetcherService;
import com.stylight.currencyconverter.services.impl.utils.datafetcher.DataFetcher;
import com.stylight.currencyconverter.services.impl.utils.datafetcher.DataFetcherFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by prasadus on 08/03/17.
 */
@Service
public class DataFetcherServiceImpl implements DataFetcherService {
    DATAFETCHER_TYPE datafetcherType = DATAFETCHER_TYPE.ECB;
    DataFetcher dataFetcher;

    DataFetcherServiceImpl() {
        dataFetcher = DataFetcherFactory.getDataFetcher(datafetcherType);
    }

    @Cacheable("conversion_rate")
    public ConversionRateDTO getConversionRate(String fromCurrency, String toCurrency) {
        Double conversionRate = dataFetcher.getConversionRate(fromCurrency, toCurrency);
        ConversionRateDTO conversionRateDTO = new ConversionRateDTO();
        conversionRateDTO.setSourceCurrency(fromCurrency);
        conversionRateDTO.setTargetCurrency(toCurrency);
        conversionRateDTO.setConversionRate(conversionRate);
        return conversionRateDTO;
    }
}
