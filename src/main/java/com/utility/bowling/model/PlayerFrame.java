package com.utility.bowling.model;

public class PlayerFrame {

    private int frameNumber;
    private AttemptPins attemptPins1;
    private AttemptPins attemptPins2;
    private AttemptPins attemptPins3;

    public PlayerFrame(int frameNumber){
        this.frameNumber = frameNumber;
    }

    public AttemptPins getAttemptPins1() {
        return attemptPins1;
    }

    public void setAttemptPins1(AttemptPins attemptPins1) {
        this.attemptPins1 = attemptPins1;
    }

    public AttemptPins getAttemptPins2() {
        return attemptPins2;
    }

    public void setAttemptPins2(AttemptPins attemptPins2) {
        this.attemptPins2 = attemptPins2;
    }

    public AttemptPins getAttemptPins3() {
        return attemptPins3;
    }

    public void setAttemptPins3(AttemptPins attemptPins3) {
        this.attemptPins3 = attemptPins3;
    }

    public int getFrameNumber() {
        return frameNumber;
    }

    public void setFrameNumber(int frameNumber) {
        this.frameNumber = frameNumber;
    }
}
