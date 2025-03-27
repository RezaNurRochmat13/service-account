package com.mega.service.account;

import com.mega.service.account.module.account.dto.CreateAccountDto;
import com.mega.service.account.module.account.dto.ListAccountDto;
import com.mega.service.account.module.account.service.AccountServiceImpl;
import com.mega.service.account.module.transactions.dto.CreateTransactionDto;
import com.mega.service.account.module.transactions.dto.ListTransactionDto;
import com.mega.service.account.module.transactions.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TransactionPresenterTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private AccountServiceImpl accountService;

    @MockBean
    private TransactionServiceImpl transactionService;

    @Test
    public void testFindAllActiveAccounts() {
        List<ListAccountDto> mockAccounts = List.of(new ListAccountDto("12345", "John Doe", 1000.0));
        Mockito.when(accountService.findAllActiveAccounts()).thenReturn(mockAccounts);

        ResponseEntity<Map> response = restTemplate.getForEntity("/api/accounts", Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    public void testCheckAccountBalance() {
        Mockito.when(accountService.checkAccountBalance("12345")).thenReturn(1000.0);

        ResponseEntity<Map> response = restTemplate.getForEntity("/api/accounts/balance/12345", Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        assertEquals(1000.0, response.getBody().get("data"));
    }

    @Test
    public void testCreateNewAccount() {
        CreateAccountDto request = new CreateAccountDto("John Doe", 500.0);
        ListAccountDto mockResponse = new ListAccountDto("12345", "John Doe", 500.0);
        Mockito.when(accountService.createNewAccount(Mockito.any(CreateAccountDto.class))).thenReturn(mockResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateAccountDto> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/api/accounts", entity, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    public void testFindAllActiveTransactions() {
        List<ListAccountDto> mockTransactions = List.of(new ListAccountDto("TX123", "12345", 500.0));
        Mockito.when(transactionService.findAllActiveTransactions()).thenReturn(mockTransactions);

        ResponseEntity<Map> response = restTemplate.getForEntity("/api/transactions", Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        assertNotNull(response.getBody().get("data"));
    }

    @Test
    public void testCreateNewTransaction() {
        CreateTransactionDto request = new CreateTransactionDto("12345", 200.0);
        ListTransactionDto mockResponse = new ListTransactionDto("TX123", "12345", 200.0);
        Mockito.when(transactionService.createNewTransaction(Mockito.any(CreateTransactionDto.class))).thenReturn(mockResponse);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CreateTransactionDto> entity = new HttpEntity<>(request, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity("/api/transactions", entity, Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("success", response.getBody().get("status"));
        assertNotNull(response.getBody().get("data"));
    }

}
