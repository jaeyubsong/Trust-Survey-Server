package com.thetrustlesstrio.TrustSurveyServer;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "surveys") // change it properly
public class Survey {
    @Id
    private String id;

    private String publisherWalletId;

    private String title;
    private String summary;
    private String desc;

    private String privateAttendeeKey;
    private String publicAttendeeEmailPattern; // ex. '@kaist.ac.kr'

    private int maxAttendeeCount;

    @Schema(type = "LocalDateTime", example = "2023-05-05T13:30:01Z")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime automaticClosingDatetime;

    private boolean manualClosing;
    private int reward;

    private List<String> questions;

    // TODO: 현재 응답 목록 ((참여자1, 응답1), (참여자2, 응답2), ...) -> 게시자만 볼 수 있어야 하는 필드

    public Survey() {}

    public Survey(String publisherWalletId,
                  String title,
                  String summary,
                  String desc,
                  String privateAttendeeKey,
                  String publicAttendeeEmailPattern,
                  int maxAttendeeCount,
                  LocalDateTime automaticClosingDatetime,
                  boolean manualClosing,
                  int reward,
                  List<String> questions) {
        this.publisherWalletId = publisherWalletId;
        this.title = title;
        this.summary = summary;
        this.desc = desc;
        this.privateAttendeeKey = privateAttendeeKey;
        this.publicAttendeeEmailPattern = publicAttendeeEmailPattern;
        this.maxAttendeeCount = maxAttendeeCount;
        this.automaticClosingDatetime = automaticClosingDatetime;
        this.manualClosing = manualClosing;
        this.reward = reward;
        this.questions = List.copyOf(questions);
    }

    public Survey(RegisterSurveyDto dto) {
        this.publisherWalletId = dto.getPublisherWalletId();
        this.title = dto.getTitle();
        this.summary = dto.getSummary();
        this.desc = dto.getDesc();
        this.privateAttendeeKey = dto.getPrivateAttendeeKey();
        this.publicAttendeeEmailPattern = dto.getPublicAttendeeEmailPattern();
        this.maxAttendeeCount = dto.getMaxAttendeeCount();
        this.automaticClosingDatetime = dto.getAutomaticClosingDatetime();
        this.manualClosing = dto.isManualClosing();
        this.reward = dto.getReward();
        this.questions = List.copyOf(dto.getQuestions());
    }
}