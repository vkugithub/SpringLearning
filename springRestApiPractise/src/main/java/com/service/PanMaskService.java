package com.service;

import com.domain.Account;
import org.springframework.stereotype.Service;

@Service
public class PanMaskService {
	 

	    public Account maskPanNumber(Account account) {
	        String pan = account.getPrimaryAccountNumber();
	        String MASK = "";
	        if(pan.length() == 16) {
	        	MASK="XXXXXXX";
	        }else {
	        	MASK="XXXXXXXXXX";
	        }
	        String maskNumber = pan.substring(0, 5) + MASK + pan.substring(pan.length() - 4);
	        account.setPrimaryAccountNumber(maskNumber);
	        return account;
	    }
}
