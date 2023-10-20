package com.yash.SunbaseData.Assignment.services.implementations;

import com.yash.SunbaseData.Assignment.services.AuthenticationService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public String isAuthentic(String loginId, String password) {
        Map<String, String> payload = new HashMap<>();
        payload.put("login_id", loginId);
        payload.put("password", password);

        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders());
        val response = restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp", HttpMethod.POST, httpEntity, String.class);
        String bearerToken = response.getBody().substring(19, response.getBody().length()-3);

        return bearerToken;
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
