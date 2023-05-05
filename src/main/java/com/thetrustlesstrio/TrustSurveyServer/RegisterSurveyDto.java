package com.thetrustlesstrio.TrustSurveyServer;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterSurveyDto {

    private String desc;

    private String privateAttendeeKey;
    private String publicAttendeeEmailPattern; // ex. '@kaist.ac.kr'

    private int maxAttendeeCount;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @NotNull
    private LocalDateTime automaticClosingDatetime;

    private boolean manualClosing;
    private int reward;
}
