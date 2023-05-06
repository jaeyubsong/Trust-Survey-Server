package com.thetrustlesstrio.TrustSurveyServer.controller;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // http://localhost:8080/
        return "home"; // home.html
    }

    @Hidden
    @GetMapping("test")
    public String Test(Model model) {
        // http://localhost:8080/test
        model.addAttribute("data", "Test!!");
        return "test"; // test.html
    }

    @Hidden
    @GetMapping("test-mvc")
    public String testMvc(@RequestParam(value = "name") String name, Model model) {
        // http://localhost:8080/test-mvc?name=trio
        model.addAttribute("name", name);
        return "test-template"; // test-template.html
    }

    @Hidden
    @GetMapping("test-string")
    @ResponseBody
    public String testString(@RequestParam("name") String name) {
        // http://localhost:8080/test-string?name=trio
        return "Test " + name; // Raw string
    }

    @Hidden
    @GetMapping("test-api")
    @ResponseBody
    public HomeController.Test testApi(@RequestParam("name") String name) {
        HomeController.Test test = new HomeController.Test();
        test.setName(name);
        return test; // JSON API
    }

    @Hidden
    static class Test {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
