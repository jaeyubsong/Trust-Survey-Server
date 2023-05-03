package com.thetrustlesstrio.TrustSurveyServer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SurveyRepository extends MongoRepository<Survey, String> {
    // TODO: Add non-ID-based operations here if necessary
}
