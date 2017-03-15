package com.stylight.currencyconverter.services.impl.utils.datafetcher;

import org.springframework.stereotype.Component;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.net.URL;
import java.util.HashMap;

/**
 * Created by prasadus on 09/03/17.
 *
 * NOTE: This implementation is specific to parse European Central Bank data.
 *
 */
@Component
public class ECBDataFetcher implements DataFetcher {
    // Chosen this as this can be the most reliable source of data.
    private final String ECB_DATA_URL = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
    private HashMap<String, Double> currencyMap = new HashMap<String, Double>(40);

    private HashMap<String, Double> getECBConversionDataMap() {
        currencyMap.clear();
        try {
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();
            DefaultHandler handler = new DefaultHandler() {
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes) {
                    if (localName.equals("Cube")) {
                        String currency = attributes.getValue("currency");
                        String rate = attributes.getValue("rate");
                        if (currency != null && rate != null) {
                            try {
                                currencyMap.put(currency, Double.parseDouble(rate));
                            } catch (Exception e) {
                                System.out.println("Cannot parse exchange rate.");
                                e.printStackTrace();
                            }
                        }
                    }
                }
            };
            URL url = new URL(ECB_DATA_URL);
            InputSource inputSource = new InputSource(url.openStream());
            xmlReader.setContentHandler(handler);
            xmlReader.setErrorHandler(handler);
            xmlReader.parse(inputSource);
            // For EURO.
            currencyMap.put("EUR", 1.0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyMap;
    }

    public Double getConversionRate(String fromCurrency, String toCurrency) {
        HashMap<String, Double> currencyMap = getECBConversionDataMap();
        Double conversionRate = currencyMap.get(toCurrency) / currencyMap.get(fromCurrency);
        return conversionRate;
    }
}