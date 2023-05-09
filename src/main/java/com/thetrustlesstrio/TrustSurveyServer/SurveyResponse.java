package com.thetrustlesstrio.TrustSurveyServer;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SurveyResponse {

    public SurveyResponse(String participantWalletId, List<String> answers) {
        this.participantWalletId = participantWalletId;
        this.answers = answers;
    }

    private String participantWalletId;
    private List<String> answers;
}
