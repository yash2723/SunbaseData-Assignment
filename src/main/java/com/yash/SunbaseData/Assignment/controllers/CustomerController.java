package com.yash.SunbaseData.Assignment.controllers;

import com.yash.SunbaseData.Assignment.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @GetMapping("/customer")
    public String getCustomerList(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        String token = (String) request.getSession().getAttribute("TOKEN");
        if(token.equals("")) response.sendRedirect("http://localhost:8080/");

        List<Object> customerList = customerService.getCustomerList(token);
        model.addAttribute("customerList", customerList);

        return "Home";
    }

    @PostMapping("/delete/customer")
    public void deleteCustomer(@RequestParam("uuid") String uuid, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = (String) request.getSession().getAttribute("TOKEN");
        if(token.equals("")) response.sendRedirect("http://localhost:8080/");

        customerService.deleteCustomer(token, uuid);
        response.sendRedirect("http://localhost:8080/customer");
    }

    @PostMapping("/update/page")
    public String updatePage(@RequestParam("uuid") String uuid, HttpServletRequest request) {
        request.getSession().setAttribute("updateCustomerId", uuid);
        return "UpdateCustomer";
    }

    @PostMapping("/update/customer")
    public void updateCustomer(@RequestParam("fname") String fname,
                               @RequestParam("lname") String lname,
                               @RequestParam("street") String street,
                               @RequestParam("address") String address,
                               @RequestParam("city") String city,
                               @RequestParam("state") String state,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = (String) request.getSession().getAttribute("TOKEN");
        String uuid = (String) request.getSession().getAttribute("updateCustomerId");
        if(token.equals("")) response.sendRedirect("http://localhost:8080/");

        customerService.updateCustomer(token, uuid, fname, lname, street, address, city, state, email, phone);
        response.sendRedirect("http://localhost:8080/customer");
    }

    @GetMapping("/add/page")
    public String addPage(HttpServletRequest request) {
        return "AddCustomer";
    }

    @PostMapping("/add/customer")
    public void addCustomer(@RequestParam("fname") String fname,
                               @RequestParam("lname") String lname,
                               @RequestParam("street") String street,
                               @RequestParam("address") String address,
                               @RequestParam("city") String city,
                               @RequestParam("state") String state,
                               @RequestParam("email") String email,
                               @RequestParam("phone") String phone,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = (String) request.getSession().getAttribute("TOKEN");
        if(token.equals("")) response.sendRedirect("http://localhost:8080/");

        customerService.addCustomer(token, fname, lname, street, address, city, state, email, phone);
        response.sendRedirect("http://localhost:8080/customer");
    }
}