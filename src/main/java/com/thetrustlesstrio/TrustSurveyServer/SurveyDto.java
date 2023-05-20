package com.thetrustlesstrio.TrustSurveyServer;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@Setter
public class SurveyDto {
    private String id;

    private String publisherWalletId;

    private String title;
    private String summary;
    private String desc;

    private String privateAttendeeKey;
    private String publicAttendeeEmailPattern;

    private int maxAttendeeCount;

    @Schema(type = "LocalDateTime", example = "2023-05-05T13:30:01Z")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime automaticClosingDatetime;

    private boolean manualClosing;
    private boolean isManuallyClosed;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean isClosed;

    public boolean isClosed() {
        if (this.manualClosing && this.isManuallyClosed) {
            return true;
        }

        LocalDateTime now = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.UTC);
        return now.isAfter(this.automaticClosingDatetime);
    }

    private int reward;

    private List<String> questions;

    // TODO: 현재 응답 목록 ((참여자1, 응답1), (참여자2, 응답2), ...) -> 게시자만 볼 수 있어야 하는 필드
    private List<SurveyResponse> responses;

    public SurveyDto(Survey survey) {
        this.id = survey.getId();

        this.publisherWalletId = survey.getPublisherWalletId();
        this.title = survey.getTitle();
        this.summary = survey.getSummary();
        this.desc = survey.getDesc();
        this.privateAttendeeKey = survey.getPrivateAttendeeKey();
        this.publicAttendeeEmailPattern = survey.getPublicAttendeeEmailPattern();
        this.maxAttendeeCount = survey.getMaxAttendeeCount();
        this.automaticClosingDatetime = survey.getAutomaticClosingDatetime();
        this.manualClosing = survey.isManualClosing();
        this.isManuallyClosed = survey.isManuallyClosed();
        this.reward = survey.getReward();
        this.questions = List.copyOf(survey.getQuestions());
        this.responses = List.copyOf(survey.getResponses());
    }
}
