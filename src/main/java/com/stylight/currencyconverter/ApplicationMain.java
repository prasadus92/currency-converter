package com.stylight.currencyconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by prasadus on 08/03/17.
 */
@SpringBootApplication
@EnableCaching
public class ApplicationMain {
    public static void main(String args[]) {
        SpringApplication.run(ApplicationMain.class, args);
    }
}
