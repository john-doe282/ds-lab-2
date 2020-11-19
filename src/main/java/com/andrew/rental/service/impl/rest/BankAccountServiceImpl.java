package com.andrew.rental.service.impl.rest;

import com.andrew.rental.model.BankAccount;
import com.andrew.rental.service.BankAccountService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public final class BankAccountServiceImpl implements BankAccountService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = System.getenv("BANK_URL") + ":8081/bank";

    private void performPostRequest(String url, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);

    }

    @Override
    public void addBankAccount(Map<String, Object> bankAccount) {
        performPostRequest(baseUrl, bankAccount);
    }

    @Override
    public BankAccount getBankAccountById(UUID id)  {
        String requestUrl = baseUrl + "/" + id.toString();

        return restTemplate.getForObject(requestUrl, BankAccount.class);
    }

    @Override
    public void deleteBankAccountById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();

        restTemplate.delete(requestUrl);
    }

    @Override
    public List<BankAccount> getBankAccountsByUserId(UUID id) {
        String requestUrl = baseUrl + "/user/" + id.toString();

        return restTemplate.getForObject(requestUrl, List.class);
    }
}
