package com.thetrustlesstrio.TrustSurveyServer.controller;

import com.thetrustlesstrio.TrustSurveyServer.RegisterSurveyDto;
import com.thetrustlesstrio.TrustSurveyServer.Survey;
import com.thetrustlesstrio.TrustSurveyServer.SurveyRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Survey Controller", description = "Survey 관련 컨트롤러")
@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepo;

    @Operation(summary = "get all surveys", description = "등록된 모든 survey를 받아오는 API. 아직은 유저 별로 다르게 보여주는 기능은 없음.")
    @GetMapping("survey")
    @ResponseBody
    public List<Survey> listSurvey() {
        return surveyRepo.findAll();
    }

    @Operation(summary = "get survey by id", description = "등록된 survey 중에서 id={id} 에 맞는 survey를 받아오는 API. 아직은 유저 별로 다르게 보여주는 기능은 없음.")
    @GetMapping("survey/{id}")
    @ResponseBody
    public Optional<Survey> getSurvey(@PathVariable("id") String id) {
        return surveyRepo.findById(id);
    }

    @Operation(summary = "register single survey", description = "새로운 survey를 등록하는 API")
    @PostMapping("survey")
    @ResponseBody
    public Survey registerSurvey(@RequestBody @Valid RegisterSurveyDto reqBody) {
        Survey survey = new Survey(reqBody);
        surveyRepo.save(survey);
        return survey;
    }
}
