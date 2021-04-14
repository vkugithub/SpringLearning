package com.springLearning.microservices.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.springLearning.microservices.currencyconversionservice.model.CurrencyConversionBean;
import com.springLearning.microservices.currencyconversionservice.service.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class CurrencyConversionController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CurrencyExchangeServiceProxy proxy;

//    @HystrixCommand(fallbackMethod="getDefaultConversionResponse")
    @GetMapping("/currency-converter-direct/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversionBean conversion(@PathVariable String from, @PathVariable String to,
                                             @PathVariable BigDecimal amount) {

        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/from/{from}/to/{to}",
                CurrencyConversionBean.class, uriVariables);

        return new CurrencyConversionBean(responseEntity.getBody().getId(),from, to, amount, responseEntity.getBody().getInputAmount().multiply(amount));
    }

    @Transactional
    public CurrencyConversionBean getDefaultConversionResponse(String from, String to,
                                                               BigDecimal amount){
        return new CurrencyConversionBean(0L,from, to, BigDecimal.ONE, BigDecimal.ONE.multiply(amount));
    }

    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{amount}")
    public CurrencyConversionBean conversionFeign(@PathVariable("from") String from, @PathVariable("to") String to,
                                                  @PathVariable("amount") BigDecimal amount) {
        CurrencyConversionBean result = proxy.findByCurrencyFromAndCurrencyTo(from, to);

        log.info(" response from conversation exchange service "+result);

        return new CurrencyConversionBean(result.getId(),from, to, amount, result.getInputAmount().multiply(amount));
    }

}
