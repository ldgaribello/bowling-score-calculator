package com.utility.bowling.service;

import com.utility.bowling.model.PlayerFrame;

import java.util.List;

public interface BowlingScoreService {

    /**
     * Process a list of frames and calculates its score.
     *
     * @param frames
     */
    void processPlayerFrames(List<PlayerFrame> frames);
}
