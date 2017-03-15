package com.stylight.currencyconverter.services.impl.utils.datafetcher;

import com.stylight.currencyconverter.entities.enums.DATAFETCHER_TYPE;

/**
 * Created by prasadus on 09/03/17.
 *
 * For each new DataFetcher implementation, add a case to the switch statement below.
 *
 */
public class DataFetcherFactory {
    private static DataFetcher dataFetcher;
    private static boolean isInitialized;

    private static void initialize(DATAFETCHER_TYPE datafetcherType) {
        isInitialized = true;
        switch (datafetcherType) {
            case ECB:
                dataFetcher = new ECBDataFetcher();
                break;
            default:
                dataFetcher = new ECBDataFetcher();
        }
    }

    public static DataFetcher getDataFetcher(DATAFETCHER_TYPE datafetcherType) {
        if (!isInitialized) {
            initialize(datafetcherType);
        }
        return dataFetcher;
    }
}
