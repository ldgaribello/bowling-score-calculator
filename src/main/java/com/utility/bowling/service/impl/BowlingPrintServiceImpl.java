package com.utility.bowling.service.impl;

import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingPrintService;

import java.util.List;

public class BowlingPrintServiceImpl implements BowlingPrintService {

    @Override
    public void initUI() {
        System.out.flush();
        System.out.println("Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t");
    }

    @Override
    public void printPlayerScore(String playerName, List<PlayerFrame> frames) {
        System.out.print("\n" + playerName);
        System.out.print("\nPins\t");

        for (PlayerFrame frame : frames) {
            printPlayerFrameScore(frame);
        }

        int totalScore = 0;
        System.out.print("\nScore\t");
        for (PlayerFrame frame : frames) {
            totalScore += frame.getFrameScore().getScore();
            System.out.print(totalScore + "\t\t");
        }
    }

    @Override
    public void printPlayerFrameScore(PlayerFrame playerFrame) {
        // Print the first attempt score
        if (playerFrame.getAttemptPins1() != null) {
            System.out.print(playerFrame.getAttemptPins1().getSymbol());
        }
        System.out.print("\t");

        // Print the second attempt score (If any)
        if (playerFrame.getAttemptPins2() != null) {
            System.out.print(playerFrame.getAttemptPins2().getSymbol());
        }
        System.out.print("\t");

        // Print the third attempt score (If any)
        if (playerFrame.getFrameNumber() == 10 && playerFrame.getAttemptPins3() != null) {
            System.out.print(playerFrame.getAttemptPins3().getSymbol());
        }
    }

    @Override
    public void printFooter() {
        System.out.print("\n\n('X') Strike, ('/') Spare, ('F') Fault, ('E') Error");
    }
}
