package com.utility.bowling.service;

import com.utility.bowling.model.PlayerAttempt;

import java.util.List;
import java.util.Map;

public interface BowlingParserService {

    /**
     * Process a list of Strings representing a bowling game score lines
     *
     * @param scoreFileLines List representing the bowling game score lines
     * @return Score by player
     */
    Map<String, List<PlayerAttempt>> parseScoreLines(List<String> scoreFileLines);
}
