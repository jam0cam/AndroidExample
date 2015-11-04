package com.jitse.example.retrofit;

/**
 * Created by jitse on 11/5/14.
 */

public class Review  extends JsonIgnorable {
    boolean hasHalfStarint;
    int averageOverallRating;
    int displayAverageRating;
    int totalReviewCount;

    public boolean isHasHalfStarint() {
        return hasHalfStarint;
    }

    public void setHasHalfStarint(boolean hasHalfStarint) {
        this.hasHalfStarint = hasHalfStarint;
    }

    public int getAverageOverallRating() {
        return averageOverallRating;
    }

    public void setAverageOverallRating(int averageOverallRating) {
        this.averageOverallRating = averageOverallRating;
    }

    public int getDisplayAverageRating() {
        return displayAverageRating;
    }

    public void setDisplayAverageRating(int displayAverageRating) {
        this.displayAverageRating = displayAverageRating;
    }

    public int getTotalReviewCount() {
        return totalReviewCount;
    }

    public void setTotalReviewCount(int totalReviewCount) {
        this.totalReviewCount = totalReviewCount;
    }
}
