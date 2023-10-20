package com.yash.SunbaseData.Assignment.services.implementations;

import com.yash.SunbaseData.Assignment.services.CustomerService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private RestTemplate restTemplate;
    @Override
    public List<Object> getCustomerList(String token) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders(token));
        val response = restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list", HttpMethod.GET, httpEntity, List.class);
        return response.getBody();
    }

    @Override
    public boolean deleteCustomer(String token, String uuid) {
        HttpEntity httpEntity = new HttpEntity(getHttpHeaders(token));
        val response = restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=delete&uuid="+uuid, HttpMethod.POST, httpEntity, String.class);
        return true;
    }

    @Override
    public void updateCustomer(String token, String uuid, String fname, String lname, String street, String address, String city, String state, String email, String phone) {
        Map<String, String> payload = new HashMap<>();
        payload.put("first_name", fname);
        payload.put("last_name", lname);
        payload.put("street", street);
        payload.put("address", address);
        payload.put("city", city);
        payload.put("state", state);
        payload.put("email", email);
        payload.put("phone", phone);

        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders(token));
        val response = restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=update&uuid="+uuid, HttpMethod.POST, httpEntity, String.class);
    }

    @Override
    public void addCustomer(String token, String fname, String lname, String street, String address, String city, String state, String email, String phone) {
        Map<String, String> payload = new HashMap<>();
        payload.put("first_name", fname);
        payload.put("last_name", lname);
        payload.put("street", street);
        payload.put("address", address);
        payload.put("city", city);
        payload.put("state", state);
        payload.put("email", email);
        payload.put("phone", phone);

        HttpEntity<Map> httpEntity = new HttpEntity<>(payload, getHttpHeaders(token));
        val response = restTemplate.exchange("https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create", HttpMethod.POST, httpEntity, String.class);
    }

    public HttpHeaders getHttpHeaders(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        return httpHeaders;
    }
}
