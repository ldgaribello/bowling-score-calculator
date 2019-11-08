package com.utility.bowling.service;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;

import java.util.List;

public interface BowlingFrameService {

    /**
     * Calculate the score given a list of attempts corresponding to a single player.
     *
     * @param playerAttempts One player attempts
     */
    List<PlayerFrame> aggregateFrames(List<PlayerAttempt> playerAttempts);
}
