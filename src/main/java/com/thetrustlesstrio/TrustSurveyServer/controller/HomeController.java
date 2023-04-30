package com.thetrustlesstrio.TrustSurveyServer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        http://localhost:8080/
        return "home"; // home.html
    }

}
