package com.springLearning.microservices.currencyconversionservice.service;


import com.springLearning.microservices.currencyconversionservice.model.CurrencyConversionBean;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000") // Insted of using hardcore url use ribbon as a load balancer
//@FeignClient(name = "currency-exchange-service")
@FeignClient(name = "zuul-api-gateway-server") //call through zull api gateway
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
   // @RequestMapping(method=RequestMethod.GET, value = "/currency-exchange/from/{from}/to/{to}")
    @RequestMapping(method=RequestMethod.GET, value = "/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    CurrencyConversionBean findByCurrencyFromAndCurrencyTo(@PathVariable("from") String from,
                                                           @PathVariable("to") String to);
}