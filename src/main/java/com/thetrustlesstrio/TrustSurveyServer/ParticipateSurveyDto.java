package com.thetrustlesstrio.TrustSurveyServer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ParticipateSurveyDto {

    @NotEmpty
    private String surveyId;

    @NotEmpty
    private String participantWalletId;

    @NotEmpty
    private List<String> answers;
}
