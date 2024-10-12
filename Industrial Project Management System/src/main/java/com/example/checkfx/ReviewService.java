package com.example.checkfx;

public interface ReviewService {
    void evaluateProjectTitle(String projectTitle);
    void communicateStatusToCoordinator(String status);
    void writeRecommendationsForRevision();

}

