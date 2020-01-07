package com.llyods.pan.validation;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.llyods.pan.validation.domain.Account;
import com.llyods.pan.validation.domain.Status;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PanValidationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PanValidationApplicationTestsByRestTemplate {

    @LocalServerPort
    private int port;
    private TestRestTemplate template = new TestRestTemplate();

    @Test
    public void testPan() {
        Account account = new Account();
        account.setPrimaryAccountNumber("4514170000000001");
        String maskPan = "45141XXXXXXX0001";
        ResponseEntity<Account> response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody().getPrimaryAccountNumber(), maskPan);
        assertEquals(response.getBody().getStatus(), Status.SUCCESS.name());
    
        account.setPrimaryAccountNumber("4514170000000000001");
        maskPan = "45141XXXXXXXXXX0001";
        
        response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 200);
        assertEquals(response.getBody().getPrimaryAccountNumber(), maskPan);
        assertEquals(response.getBody().getStatus(), Status.SUCCESS.name());
    }

    @Test
    public void testInvalidPan() {
    	Account account = new Account();
        account.setPrimaryAccountNumber(null);
        ResponseEntity<Account> response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 400);
        assertEquals(response.getBody().getStatus(), Status.FAILURE.name());
        
        account.setPrimaryAccountNumber("");
        response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 400);
        assertEquals(response.getBody().getStatus(), Status.FAILURE.name());
              
        account.setPrimaryAccountNumber("5514170000000000001");
        response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 400);
        assertEquals(response.getBody().getStatus(), Status.FAILURE.name());
        
        account.setPrimaryAccountNumber("A514170000000000001");
        response = template.postForEntity(getUrl(), account, Account.class);
        assertEquals(response.getStatusCodeValue(), 400);
        assertEquals(response.getBody().getStatus(), Status.FAILURE.name());
    }

    private String getUrl() {
        return "http://localhost:" + port + "/customerrules/v1/pan";
    }
}
