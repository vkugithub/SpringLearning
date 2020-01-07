package com.llyods.pan.validation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.llyods.pan.validation.controller.PanValidationController;
import com.llyods.pan.validation.domain.Account;
import com.llyods.pan.validation.service.PanMaskService;
import com.llyods.pan.validation.service.PanValidationService;

@RunWith(SpringRunner.class)
@WebMvcTest(PanValidationController.class)
public class PanValidationApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private PanValidationService validationService;
	
	@MockBean
	private PanMaskService maskService;	
	

	@Test
	public void testPan() throws Exception {
		
		//Test case 1
		Account account = new Account();
		account.setPrimaryAccountNumber("45141XXXXXXX0001");		
		String requestBody = "{\"primaryAccountNumber\": \"4514170000000001\",\"status\": \"SUCCESS\"}";
		when(validationService.validatePan(any())).thenReturn(true);
		when(maskService.maskPanNumber(any())).thenReturn(account);		
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		//Test case 2		
		requestBody = "{\"primaryAccountNumber\": \"4514170000000001\",\"status\": \"SUCCESS\"}";
		when(validationService.validatePan(any())).thenReturn(true);		
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
		
		//Test case 3
		requestBody = "{\"primaryAccountNumber\": \"4514170000000001\",\"status\": \"SUCCESS\"}";		
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testInvalidPan() throws Exception {
		String requestBody = "{\"primaryAccountNumber\": \"5514170000000001\"}";
		//Test case 1
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
		
		//Test case 2
		when(validationService.validatePan(any())).thenReturn(false);
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
		
		//Test case 3
	    requestBody = "{\"primaryAccountNumber\": \"A514170000000001\"}";	    
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
		
		//Test case 4
		requestBody = "{\"primaryAccountNumber\": \"5514170000000001\"}";	    
		mvc.perform(
				MockMvcRequestBuilders.post("/customerrules/v1/pan").content(requestBody).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());
	}

}

