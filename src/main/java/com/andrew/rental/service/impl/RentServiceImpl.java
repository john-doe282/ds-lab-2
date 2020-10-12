package com.andrew.rental.service.impl;

import com.andrew.rental.model.*;
import com.andrew.rental.service.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class RentServiceImpl implements RentService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl = System.getenv("RENTS_URL") + ":8085/rents";

    private void performPostRequest(String url, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);

    }

    @Override
    public void rent(Map<String, Object> rent) throws IllegalAccessException {
        performPostRequest(baseUrl, rent);
    }

    @Override
    public ActiveRent getActiveRentById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        return restTemplate.getForObject(requestUrl, ActiveRent.class);
    }

    @Override
    public void closeRentById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        restTemplate.delete(requestUrl);
    }

    @Override
    public List<ActiveRent> activeRentsForUserId(UUID id) {
        String requestUrl = baseUrl + "/user/" + id.toString();
        return restTemplate.getForObject(requestUrl, List.class);
    }
}
