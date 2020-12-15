package com.andrew.rental.service.rest.impl;

import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.User;
import com.andrew.rental.service.rest.BankAccountService;
import com.andrew.rental.service.rest.CarService;
import com.andrew.rental.service.rest.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = System.getenv("USERS_URL") + ":8082/users";

    private final BankAccountService bankAccountService;

    @Autowired
    public UserServiceImpl(BankAccountService bankAccountService) {
        String host = System.getenv("USERS_URL");
        if (!host.startsWith("http://")) {
            baseUrl = "http://" + host + ":8082/users";
        }
        this.bankAccountService = bankAccountService;
    }

    private void performPostRequest(String url, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);

    }

    @Override
    public void addUser(Map<String, Object> user) {
        performPostRequest(baseUrl, user);
    }

    @Override
    public List<User> findAll() {
        return Arrays.asList(restTemplate.getForObject(baseUrl, User[].class));
    }

    @Override
    public UserDTO getUserById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        User user = restTemplate.getForObject(requestUrl, User.class);
        List<BankAccount> bankAccounts = bankAccountService.
                getBankAccountsByUserId(id);
        UserDTO userDTO = UserDTO.fromUser(user, bankAccounts);
        return UserDTO.fromUser(user, bankAccounts);
    }

    @Override
    public void deleteUserById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        restTemplate.delete(requestUrl);
    }
}
