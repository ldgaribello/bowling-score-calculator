package com.utility.bowling;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingFrameService;
import com.utility.bowling.service.BowlingParserService;
import com.utility.bowling.service.BowlingScoreService;
import com.utility.bowling.service.impl.BowlingFrameServiceImpl;
import com.utility.bowling.service.impl.BowlingParserServiceImpl;
import com.utility.bowling.service.impl.BowlingScoreServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingCalculator {

    public static void main(String[] args) throws Exception {
        BowlingParserService bowlingParserService = new BowlingParserServiceImpl();
        BowlingFrameService bowlingFrameService = new BowlingFrameServiceImpl();
        BowlingScoreService bowlingScoreService = new BowlingScoreServiceImpl();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter bowling score file path: ");
        String filePath = scanner.next();

        List<String> lines = Collections.emptyList();
        // Read file and get Lines
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            lines = stream.collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Could not read file");
            System.exit(0);
        }

        // Parse lines
        Map<String, List<PlayerAttempt>> playerAttempts = bowlingParserService.parseScoreLines(lines);

        Map<String, List<PlayerFrame>> playerFrames = new HashMap<>();
        playerAttempts.forEach((player, attempts) -> {
            // Aggregate Frames
            List<PlayerFrame> frames = bowlingFrameService.aggregatePins(attempts);
            // Process Score
            bowlingScoreService.processPlayerFrames(frames);

            playerFrames.put(player, frames);
        });

        System.out.println("Frame\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10\t");
        playerFrames.forEach((player, frames) -> {
            System.out.print("\n" + player);

            System.out.print("\nPins\t");
            for (PlayerFrame frame : frames) {
                if (frame.getAttemptPins1() != null) {
                    System.out.print(frame.getAttemptPins1().getSymbol());
                }
                System.out.print("\t");

                if (frame.getAttemptPins2() != null) {
                    System.out.print(frame.getAttemptPins2().getSymbol());
                }
                System.out.print("\t");

                if (frame.getFrameNumber() == 10 && frame.getAttemptPins3() != null) {
                    System.out.print(frame.getAttemptPins3().getSymbol());
                }
            }

            int totalScore = 0;
            System.out.print("\nScore\t");
            for (PlayerFrame frame : frames) {
                totalScore += frame.getFrameScore().getScore();
                System.out.print(totalScore + "\t\t");
            }
        });
        System.out.print("\n\n('X') Strike, ('/') Spare, ('F') Fault, ('E') Error");
        System.out.print("\n\n\n\nPress Enter key to continue...\n");
        System.in.read();
    }
}
