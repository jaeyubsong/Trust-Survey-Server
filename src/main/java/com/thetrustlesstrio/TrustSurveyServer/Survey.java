package com.thetrustlesstrio.TrustSurveyServer;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "surveys") // change it properly
public class Survey {
    @Id
    private String id;

    private String desc;
    // ...
    private int reward;

    public Survey(String desc, int reward) {
        this.desc = desc;
        this.reward = reward;
    }
}