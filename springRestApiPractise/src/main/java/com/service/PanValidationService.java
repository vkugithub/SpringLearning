package com.service;

import org.springframework.stereotype.Service;

@Service
public class PanValidationService {

	public boolean validatePan(String pan) {
		 if (pan == null || !(pan.length() == 16 || pan.length() == 19)) {
	            return false;        	
	        }else if(!(pan.charAt(0)=='4')){
	        	return false;
	        }
		 else if(!isNumeric(pan)) {
	        	return false;
	        }
		 return true;
	}
	
	public boolean isNumeric(String strNum) {
	    return strNum.matches("-?\\d+(\\.\\d+)?");
	}
}
