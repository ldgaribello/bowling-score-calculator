package com.utility.bowling.service.impl;

import com.utility.bowling.model.AttemptPins;
import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingFrameService;

import java.util.LinkedList;
import java.util.List;

public class BowlingFrameServiceImpl implements BowlingFrameService {

    @Override
    public List<PlayerFrame> aggregateFrames(List<PlayerAttempt> playerAttempts) {
        int currentPins = 0;
        int currentChance = 1;
        int currentFrame = 1;

        List<PlayerFrame> playerFrames = new LinkedList<>();

        PlayerFrame playerFrame = new PlayerFrame(currentFrame);

        for (PlayerAttempt playerAttempt : playerAttempts) {
            AttemptPins attemptPins = getAttemptPins(playerAttempt);

            currentPins += attemptPins.getPins();

            if (currentFrame == 10) {
                if (currentChance == 1) {
                    playerFrame.setAttemptPins1(attemptPins);
                    if(currentPins == 10){
                        attemptPins.setSymbol("X");
                        currentPins = 0;
                    }
                } else if (currentChance == 2) {
                    playerFrame.setAttemptPins2(attemptPins);
                    if ("X".equals(playerFrame.getAttemptPins1().getSymbol())) {
                        if (currentPins == 10) {
                            attemptPins.setSymbol("X");
                            currentPins = 0;
                        }
                    } else {
                        if (currentPins == 10) {
                            attemptPins.setSymbol("/");
                            currentPins = 0;
                        } else {
                            playerFrames.add(playerFrame);
                            break;
                        }
                    }
                } else if (currentChance == 3) {
                    playerFrame.setAttemptPins3(attemptPins);
                    if (("X".equals(playerFrame.getAttemptPins2().getSymbol()) || "/".equals(playerFrame.getAttemptPins2().getSymbol()))
                            && currentPins == 10) {
                        attemptPins.setSymbol("X");
                    } else if (currentPins == 10) {
                        attemptPins.setSymbol("/");
                    }
                    playerFrames.add(playerFrame);
                    break;
                }
                currentChance++;
            } else {
                if (currentChance == 1) {
                    playerFrame.setAttemptPins1(attemptPins);
                    if (currentPins == 10) {
                        attemptPins.setSymbol("X");

                        playerFrames.add(playerFrame);
                        playerFrame = new PlayerFrame(++currentFrame);
                        currentChance = 1;
                        currentPins = 0;
                    } else {
                        currentChance++;
                    }
                } else if (currentChance == 2) {
                    playerFrame.setAttemptPins2(attemptPins);
                    if (currentPins == 10) {
                        attemptPins.setSymbol("/");
                    }

                    playerFrames.add(playerFrame);
                    playerFrame = new PlayerFrame(++currentFrame);
                    currentChance = 1;
                    currentPins = 0;
                }
            }
        }
        return playerFrames;
    }

    private AttemptPins getAttemptPins(PlayerAttempt playerAttempt) {
        AttemptPins attemptPins = new AttemptPins();

        if (playerAttempt.getError()) {
            attemptPins.setSymbol("E");
            attemptPins.setPins(0);
        } else if (playerAttempt.getAttemptScore() instanceof String) {
            attemptPins.setSymbol("F");
            attemptPins.setPins(0);
        } else {
            Integer pins = (Integer) playerAttempt.getAttemptScore();

            attemptPins.setSymbol(String.valueOf(pins));
            attemptPins.setPins(pins);
        }
        return attemptPins;
    }
}
