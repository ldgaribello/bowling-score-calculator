package com.utility.bowling.service;

import com.utility.bowling.model.PlayerFrame;

import java.util.List;

public interface BowlingPrintService {

    /**
     * Clear the UI and set the score table
     */
    void initUI();

    /**
     * Print the player's score frame by frame
     *
     * @param playerName player's name
     * @param playerScore player's game score
     */
    void printPlayerScore(String playerName, List<PlayerFrame> playerScore);

    /**
     * Print the player's frame score
     *
     * @param playerFrame player's frame score
     */
    void printPlayerFrameScore(PlayerFrame playerFrame);

    /**
     * Print the UI legends
     */
    void printFooter();
}
