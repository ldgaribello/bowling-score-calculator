package com.utility.bowling.model;

public class FrameScore {

    private int score;
    private int aggregatedScore;
    private String scoreType;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAggregatedScore() {
        return aggregatedScore;
    }

    public void setAggregatedScore(int aggregatedScore) {
        this.aggregatedScore = aggregatedScore;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }
}
