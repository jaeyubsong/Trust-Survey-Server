package com.thetrustlesstrio.TrustSurveyServer;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "surveys") // change it properly
public class Survey {
    @Id
    private String id;

    private String desc;

    private String privateAttendeeKey;
    private String publicAttendeeEmailPattern; // ex. '@kaist.ac.kr'

    private int maxAttendeeCount;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    private LocalDateTime automaticClosingDatetime;

    private boolean manualClosing;
    private int reward;

    // TODO: 현재 참여자 목록 (계좌 id)

    public Survey() {}

    public Survey(String desc,
                  String privateAttendeeKey,
                  String publicAttendeeEmailPattern,
                  int maxAttendeeCount,
                  LocalDateTime automaticClosingDatetime,
                  boolean manualClosing,
                  int reward) {
        this.desc = desc;
        this.privateAttendeeKey = privateAttendeeKey;
        this.publicAttendeeEmailPattern = publicAttendeeEmailPattern;
        this.maxAttendeeCount = maxAttendeeCount;
        this.automaticClosingDatetime = automaticClosingDatetime;
        this.manualClosing = manualClosing;
        this.reward = reward;
    }

    public Survey(RegisterSurveyDto dto) {
        this.desc = dto.getDesc();
        this.privateAttendeeKey = dto.getPrivateAttendeeKey();
        this.publicAttendeeEmailPattern = dto.getPublicAttendeeEmailPattern();
        this.maxAttendeeCount = dto.getMaxAttendeeCount();
        this.automaticClosingDatetime = dto.getAutomaticClosingDatetime();
        this.manualClosing = dto.isManualClosing();
        this.reward = dto.getReward();
    }
}