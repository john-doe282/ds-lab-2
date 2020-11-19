package com.andrew.rental.service.impl.rest;

import com.andrew.rental.dto.UserDTO;
import com.andrew.rental.model.BankAccount;
import com.andrew.rental.model.Car;
import com.andrew.rental.model.User;
import com.andrew.rental.service.BankAccountService;
import com.andrew.rental.service.CarService;
import com.andrew.rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = System.getenv("USERS_URL") + ":8082/users";

    @Autowired
    private CarService carService;

    @Autowired
    private BankAccountService bankAccountService;

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
        return restTemplate.getForObject(baseUrl, List.class);
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
