package org.example;

import org.example.Model.User;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://94.198.50.185:7081/api/users";

        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        String session = response.getHeaders().get("set-cookie").get(0);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", session);

        User james = new User();
        james.setName("James");
        james.setId(3l);
        james.setLastName("Brown");
        james.setAge((byte) 29);

        HttpEntity<User> requestInfo = new HttpEntity<>(james, httpHeaders);  // данные для отправки запроса
        ResponseEntity<String> response2 = restTemplate.postForEntity(url, requestInfo, String.class);
        String code1= response2.getBody();


        User thomas = new User();
        thomas.setName("Thomas");
        thomas.setId(3l);
        thomas.setLastName("Shelby");
        thomas.setAge((byte) 29);

        HttpEntity<User> requestInfo1 = new HttpEntity<>(thomas, httpHeaders);
        ResponseEntity<String> response3 = restTemplate.exchange(url, HttpMethod.PUT, requestInfo1, String.class);
        String code2 = response3.getBody();


        HttpEntity<User> requestInfo2 = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response4 = restTemplate.exchange(url + "/3", HttpMethod.DELETE, requestInfo2, String.class);
        String code3 = response4.getBody();

        System.out.println((code1 + code2 + code3).length());




    }
}