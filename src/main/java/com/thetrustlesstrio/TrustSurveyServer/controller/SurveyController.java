package com.thetrustlesstrio.TrustSurveyServer.controller;

import com.thetrustlesstrio.TrustSurveyServer.Survey;
import com.thetrustlesstrio.TrustSurveyServer.SurveyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Tag(name = "Survey Controller", description = "Survey 관련 컨트롤러")
@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepo;

    @GetMapping("test")
    public String Test(Model model) {
        // http://localhost:8080/test
        model.addAttribute("data", "Test!!");
        return "test"; // test.html
    }

    @GetMapping("test-mvc")
    public String testMvc(@RequestParam(value = "name") String name, Model model) {
        // http://localhost:8080/test-mvc?name=trio
        model.addAttribute("name", name);
        return "test-template"; // test-template.html
    }

    @GetMapping("test-string")
    @ResponseBody
    public String testString(@RequestParam("name") String name) {
        // http://localhost:8080/test-string?name=trio
        return "Test " + name; // Raw string
    }

    @GetMapping("test-api")
    @ResponseBody
    public Test testApi(@RequestParam("name") String name) {
        Test test = new Test();
        test.setName(name);
        return test; // JSON API
    }

    @Operation(summary = "mongo test", description = "Survey 2개를 Write 해보는 테스트 API")
    @GetMapping("mongo/test")
    @ResponseBody
    public List<Survey> mongoTest() {
        surveyRepo.deleteAll();

        surveyRepo.save(new Survey("test purpose1", 100));
        surveyRepo.save(new Survey("test purpose2", 200));

        return surveyRepo.findAll();
    }

    @GetMapping("mongo/survey")
    @ResponseBody
    public Optional<Survey> getSurvey(@RequestParam("id") String id) {
        return surveyRepo.findById(id);
    }


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
