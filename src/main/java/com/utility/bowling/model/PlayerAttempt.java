package com.utility.bowling.model;

public class PlayerAttempt {

    private int attemptNumber;
    private Object attemptScore;
    private boolean error;
    private String errorDescription;

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public Object getAttemptScore() {
        return attemptScore;
    }

    public boolean getError() {
        return error;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setAttemptScore(Object attemptScore) {
        this.attemptScore = attemptScore;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
