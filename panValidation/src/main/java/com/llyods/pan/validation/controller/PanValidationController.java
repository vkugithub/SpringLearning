package com.llyods.pan.validation.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import  com.llyods.pan.validation.domain.Account;
import com.llyods.pan.validation.domain.Status;
import com.llyods.pan.validation.exception.InvalidPanNumberException;
import com.llyods.pan.validation.service.PanMaskService;
import com.llyods.pan.validation.service.PanValidationService;

@RestController
public class PanValidationController {

	@Autowired
	private PanMaskService maskService;
	
	@Autowired
	private PanValidationService panValidationService;

	    @PostMapping("/customerrules/v1/pan")
	    ResponseEntity<Account> mask(@Valid @RequestBody Account account) {
	        String pan = account.getPrimaryAccountNumber();
	        if(!panValidationService.validatePan(pan)) {
	        	throw new InvalidPanNumberException("Invalid primary account number");	
	        }
	        maskService.maskPanNumber(account);
	        account.setStatus(Status.SUCCESS.name());
	        return ResponseEntity.ok(account);
	    }

	@GetMapping("/")
	ResponseEntity defaultRequest() {

		return new ResponseEntity(HttpStatus.OK);
	}
}
