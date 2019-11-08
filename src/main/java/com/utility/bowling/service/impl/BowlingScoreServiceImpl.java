package com.utility.bowling.service.impl;

import com.utility.bowling.model.FrameScore;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingScoreService;

import java.util.List;
import java.util.ListIterator;

public class BowlingScoreServiceImpl implements BowlingScoreService {

    @Override
    public void processPlayerFrames(List<PlayerFrame> frames) {
        ListIterator<PlayerFrame> framesIterator = frames.listIterator();
        while (framesIterator.hasNext()) {
            PlayerFrame currentFrame = framesIterator.next();

            // Calculate the frame score
            currentFrame.setFrameScore(getFrameScore(currentFrame));

            // Go back two frames to look for strikes
            if (currentFrame.getFrameNumber() > 1 && framesIterator.hasPrevious()) {
                // First time is the same as the current frame
                framesIterator.previous();
                // Second time is effectively the previous frame
                PlayerFrame prevLvl1Frame = framesIterator.previous();

                int prevLvl1Score = prevLvl1Frame.getFrameScore().getScore();
                if ("X".equals(prevLvl1Frame.getFrameScore().getScoreType())) {

                    if (currentFrame.getAttemptPins2() == null) {
                        prevLvl1Frame.getFrameScore().setScore(prevLvl1Score + currentFrame.getAttemptPins1().getPins());
                    } else {
                        prevLvl1Frame.getFrameScore().setScore(prevLvl1Score + currentFrame.getAttemptPins1().getPins() + currentFrame.getAttemptPins2().getPins());
                    }

                    if (framesIterator.hasPrevious()) {
                        PlayerFrame prevLvl2Frame = framesIterator.previous();

                        int prevLvl2Score = prevLvl2Frame.getFrameScore().getScore();
                        if ("X".equals(prevLvl2Frame.getFrameScore().getScoreType())) {
                            prevLvl2Frame.getFrameScore().setScore(prevLvl2Score
                                    + currentFrame.getAttemptPins1().getPins());
                        }
                        framesIterator.next();
                    }
                } else if ("/".equals(prevLvl1Frame.getFrameScore().getScoreType())) {
                    prevLvl1Frame.getFrameScore().setScore(prevLvl1Score + currentFrame.getAttemptPins1().getPins());
                }

                // First time is the same as the previous frame
                framesIterator.next();
                // Second time is effectively the next frame
                framesIterator.next();
            }
        }
    }

    private FrameScore getFrameScore(PlayerFrame currentFrame) {
        if (currentFrame.getFrameNumber() == 10) {
            return get10thFrameScore(currentFrame);
        } else {
            return getRegularFrameScore(currentFrame);
        }
    }

    private FrameScore getRegularFrameScore(PlayerFrame currentFrame) {
        FrameScore frameScore = new FrameScore();
        if ("X".equals(currentFrame.getAttemptPins1().getSymbol())) {
            frameScore.setScoreType("X");
            frameScore.setScore(10);
        } else if (currentFrame.getAttemptPins2() != null) {
            if ("/".equals(currentFrame.getAttemptPins2().getSymbol())) {
                frameScore.setScoreType("/");
                frameScore.setScore(10);
            } else {
                frameScore.setScoreType("O");
                frameScore.setScore(currentFrame.getAttemptPins1().getPins() + currentFrame.getAttemptPins2().getPins());
            }
        } else {
            frameScore.setScoreType("O");
            frameScore.setScore(currentFrame.getAttemptPins1().getPins());
        }
        return frameScore;
    }

    private FrameScore get10thFrameScore(PlayerFrame currentFrame) {
        FrameScore frameScore = new FrameScore();
        if ("X".equals(currentFrame.getAttemptPins1().getSymbol())) {
            int totalScore = 10;

            if (currentFrame.getAttemptPins2() != null) {
                totalScore += currentFrame.getAttemptPins2().getPins();
            }

            if (currentFrame.getAttemptPins3() != null) {
                totalScore += currentFrame.getAttemptPins3().getPins();
            }
            frameScore.setScore(totalScore);
        } else if ("/".equals(currentFrame.getAttemptPins2().getSymbol())) {
            if (currentFrame.getAttemptPins3() == null) {
                frameScore.setScore(10);
            } else if ("X".equals(currentFrame.getAttemptPins3().getSymbol())) {
                frameScore.setScore(20);
            } else {
                frameScore.setScore(10 + currentFrame.getAttemptPins3().getPins());
            }
        } else {
            frameScore.setScore(currentFrame.getAttemptPins1().getPins() + currentFrame.getAttemptPins2().getPins());
        }
        return frameScore;
    }
}
