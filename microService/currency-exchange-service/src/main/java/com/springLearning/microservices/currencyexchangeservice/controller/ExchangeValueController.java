package com.springLearning.microservices.currencyexchangeservice.controller;

import com.springLearning.microservices.currencyexchangeservice.entity.Currency;
import com.springLearning.microservices.currencyexchangeservice.entity.ExchangeValue;
import com.springLearning.microservices.currencyexchangeservice.entity.ExchangeValueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class ExchangeValueController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExchangeValueRepository repository;

    @Autowired
    Environment environment;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue exchangeService(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = repository.findByCurrencyFromAndCurrencyTo(Currency.valueOf(from), Currency.valueOf(to));
//        ExchangeValue exchangeValue=new ExchangeValue (Currency.valueOf(from),Currency.valueOf(to),new BigDecimal(65.5));
        exchangeValue.setId(Long.parseLong(environment.getProperty("local.server.port")));
        log.debug("Currency exchange value : "+exchangeValue);
        return exchangeValue;
    }

}
