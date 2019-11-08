package com.utility.bowling.service.impl;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.service.BowlingParserService;
import com.utility.bowling.util.ValidationUtil;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BowlingParserServiceImpl implements BowlingParserService {

    public Map<String, List<PlayerAttempt>> parseScoreLines(List<String> scoreFileLines) {
        Map<String, List<PlayerAttempt>> attemptsByPlayer = new HashMap<>();

        int currentLine = 0;
        for (String scoreFileLine : scoreFileLines) {
            currentLine++;

            String[] scoreLineDetail = scoreFileLine.split("\\s+");

            String playerName;
            PlayerAttempt playerAttempt = new PlayerAttempt();
            playerAttempt.setAttemptNumber(currentLine);

            if (scoreLineDetail.length == 0) {
                System.err.println("No data in line: " + currentLine);
                continue;
            } else if (scoreLineDetail.length == 1) {
                playerName = scoreLineDetail[0];

                playerAttempt.setAttemptScore("E");
                playerAttempt.setError(true);
                playerAttempt.setErrorDescription("Not enough data in line: " + currentLine);
            } else {
                playerName = scoreLineDetail[0];
                String playerScore = scoreLineDetail[1];

                if (ValidationUtil.isNumeric(playerScore)) {
                    Integer playerNumericScore = Integer.parseInt(playerScore);

                    if (playerNumericScore < 0 || playerNumericScore > 10) {
                        playerAttempt.setError(true);
                        playerAttempt.setErrorDescription("Number of pins is higher than expected in line: " + currentLine);
                    } else {
                        playerAttempt.setAttemptScore(playerNumericScore);
                    }
                } else if ("F".equals(playerScore)) {
                    playerAttempt.setAttemptScore(playerScore);
                } else {
                    playerAttempt.setAttemptScore("E");
                    playerAttempt.setError(true);
                    playerAttempt.setErrorDescription("Not valid score value in line: " + currentLine);
                }
            }
            List<PlayerAttempt> currentPlayerAttempts = attemptsByPlayer.getOrDefault(playerName, new LinkedList<>());
            currentPlayerAttempts.add(playerAttempt);
            attemptsByPlayer.put(playerName, currentPlayerAttempts);

        }
        return attemptsByPlayer;
    }
}
