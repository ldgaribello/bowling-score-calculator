package com.utility.bowling.service;

import com.utility.bowling.model.PlayerFrame;

import java.util.List;

public interface BowlingScoreService {

    void processPlayerFrames(List<PlayerFrame> frames);
}
