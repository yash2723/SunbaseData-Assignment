package com.yash.SunbaseData.Assignment.services;

import java.util.List;

public interface CustomerService {

    public List<Object> getCustomerList(String token);
    public boolean deleteCustomer(String token, String uuid);

    public void updateCustomer(String token, String uuid, String fname, String lname, String street, String address, String city, String state, String email, String phone);

    void addCustomer(String token, String fname, String lname, String street, String address, String city, String state, String email, String phone);
}
