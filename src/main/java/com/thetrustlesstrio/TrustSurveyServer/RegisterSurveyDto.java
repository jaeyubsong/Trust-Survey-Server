package com.thetrustlesstrio.TrustSurveyServer;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RegisterSurveyDto {

    @NotBlank
    private String id;

    // TODO: use logged in session to get publisher's identity, instead of getting it from request json
    @NotBlank
    private String publisherWalletId; // wallet id of person who registered survey

    @NotBlank
    private String title;

    private String summary; // short description which will be shown in survey card UI in main page

    @NotBlank
    private String desc; // long description

    private String privateAttendeeKey;
    private String publicAttendeeEmailPattern; // ex. '@kaist.ac.kr'

    @NotNull
    private int maxAttendeeCount;

    @Schema(type = "LocalDateTime", example = "2023-05-05T13:30:01Z")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @NotNull
    private LocalDateTime automaticClosingDatetime;

    @NotNull
    private int reward;

    @NotEmpty
    private List<String> questions;
}
