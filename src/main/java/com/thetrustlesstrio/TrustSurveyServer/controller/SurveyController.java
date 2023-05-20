package com.thetrustlesstrio.TrustSurveyServer.controller;

import com.thetrustlesstrio.TrustSurveyServer.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Tag(name = "Survey Controller", description = "Survey 관련 컨트롤러")
@Controller
public class SurveyController {

    @Autowired
    private SurveyRepository surveyRepo;

    @Operation(summary = "get all surveys", description = "등록된 모든 survey를 받아오는 API. 아직은 유저 별로 다르게 보여주는 기능은 없음.")
    @GetMapping("survey")
    @ResponseBody
    public List<SurveyDto> listSurvey() {
        return surveyRepo.findAll().stream()
                .map(survey -> new SurveyDto(survey))
                .collect(Collectors.toList());
    }

    @Operation(summary = "get survey by id", description = "등록된 survey 중에서 id={id} 에 맞는 survey를 받아오는 API. 아직은 유저 별로 다르게 보여주는 기능은 없음.")
    @GetMapping("survey/{id}")
    @ResponseBody
    public Optional<SurveyDto> getSurvey(@PathVariable("id") String id) {
        Optional<Survey> surveyOpt = surveyRepo.findById(id);
        if (surveyOpt.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(new SurveyDto(surveyOpt.get()));
    }

    @Operation(summary = "register single survey", description = "새로운 survey를 등록하는 API")
    @PostMapping("survey")
    @ResponseBody
    public SurveyDto registerSurvey(@RequestBody @Valid RegisterSurveyDto reqBody) {
        Survey survey = new Survey(reqBody);
        surveyRepo.save(survey);
        return new SurveyDto(survey);
    }

    @Operation(summary = "Participate single survey", description = "새로운 survey애 참여")
    @PostMapping("participate")
    @ResponseBody
    public SurveyDto participateSurvey(@RequestBody @Valid ParticipateSurveyDto reqBody) {
        SurveyResponse surveyResponse = new SurveyResponse(reqBody.getParticipantWalletId(), reqBody.getAnswers());
        Survey survey = surveyRepo.findById(reqBody.getSurveyId()).get();

        if (survey.getMaxAttendeeCount() > survey.getResponses().size()) {
            survey.getResponses().add(surveyResponse);
            surveyRepo.save(survey);
        }
        return new SurveyDto(survey);
    }

    @Operation(summary = "Close single survey manually", description = "publisher가 자신의 survey를 수동으로 종료")
    @GetMapping("survey/{id}/close")
    public SurveyDto closeSurvey(@PathVariable("id") @NotBlank String surveyId, @RequestParam("userWalletId") @NotBlank String userWalletId) {
        Survey survey = surveyRepo.findById(surveyId).get();

        // TODO: Better validation to check whether requester is the publisher of the survey
        if (!survey.getPublisherWalletId().equals(userWalletId)) {
            // TODO: Better error handling?
            throw new Error("Only publisher can manually close this survey");
        }

        // Does the survey have manual closing option?
        if (!survey.isManualClosing()) {
            // TODO: Better error handling?
            throw new Error("This survey doesn't have manual closing option");
        }

        if (survey.isManuallyClosed()) {
            return new SurveyDto(survey);
        }

        survey.setManuallyClosed(true);
        surveyRepo.save(survey);
        return new SurveyDto(survey);
    }

    @Operation(summary = "Remove all surveys", description = "(테스트용) 모든 설문 데이터를 삭제")
    @PostMapping("deleteAll")
    @ResponseBody
    public void deleteAllSurvey() {
        surveyRepo.deleteAll();
    }
}
