package com.andrew.rental.service.impl;

import com.andrew.rental.model.User;
import com.andrew.rental.service.UserService;
import javassist.NotFoundException;
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
    private final String baseUrl = "http://localhost:8070/";

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
    public User getUserById(UUID id) throws NotFoundException {
        String requestUrl = baseUrl + "/" + id.toString();
        return restTemplate.getForObject(requestUrl, User.class);
    }

    @Override
    public void deleteUserById(UUID id) throws NotFoundException {
        String requestUrl = baseUrl + "/" + id.toString();
        restTemplate.delete(requestUrl);
    }
}
