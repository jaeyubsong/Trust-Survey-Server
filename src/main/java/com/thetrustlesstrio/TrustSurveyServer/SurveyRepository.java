package com.thetrustlesstrio.TrustSurveyServer;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SurveyRepository extends MongoRepository<Survey, String> {
    Optional<Survey> findBySurveyId(String sid);
}
