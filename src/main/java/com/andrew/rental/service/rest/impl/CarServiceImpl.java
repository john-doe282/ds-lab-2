package com.andrew.rental.service.rest.impl;

import com.andrew.rental.model.Car;
import com.andrew.rental.service.rest.CarService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class CarServiceImpl implements CarService {
    private final RestTemplate restTemplate = new RestTemplate();
    private String baseUrl = System.getenv("CARS_URL") + ":8084/cars";

    public CarServiceImpl() {
        String host = System.getenv("CARS_URL");
        if (!host.startsWith("http://")) {
            baseUrl = "http://" + host + ":8084/cars";
        }
    }
    private void performPostRequest(String url, Map<String, Object> body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        restTemplate.postForObject(url, entity, String.class);

    }

    @Override
    public void addCar(Map<String, Object> car) {
        performPostRequest(baseUrl, car);
    }

    @Override
    public Car getCarById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        return restTemplate.getForObject(requestUrl, Car.class);
    }

    @Override
    public List<Car> findAll() {
        return restTemplate.getForObject(baseUrl, List.class);
    }

    @Override
    public void deleteCarById(UUID id) {
        String requestUrl = baseUrl + "/" + id.toString();
        restTemplate.delete(requestUrl);
    }
}
