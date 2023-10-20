package com.yash.SunbaseData.Assignment.controllers;

import com.yash.SunbaseData.Assignment.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String bearerToken = authenticationService.isAuthentic(loginId, password);
        if(bearerToken.equals("")) return "Login";

        request.getSession().setAttribute("TOKEN", bearerToken);

        response.sendRedirect("http://localhost:8080/customer");
        return "Login";
    }

}
