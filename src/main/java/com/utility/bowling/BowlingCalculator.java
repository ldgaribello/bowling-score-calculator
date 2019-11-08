package com.utility.bowling;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingFrameService;
import com.utility.bowling.service.BowlingParserService;
import com.utility.bowling.service.impl.BowlingFrameServiceImpl;
import com.utility.bowling.service.impl.BowlingParserServiceImpl;

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

        // Aggregate Frames
        Map<String, List<PlayerFrame>> playerFrames = new HashMap<>();
        playerAttempts.forEach((player, attempts) -> playerFrames.put(player, bowlingFrameService.aggregateFrames(attempts)));

        System.out.println("Frame\t\t1\t\t\t2\t\t\t3\t\t\t4\t\t\t5\t\t\t6\t\t\t7\t\t\t8\t\t\t9\t\t\t10\t\t\t");
        playerFrames.forEach((player, frames) -> {
            System.out.println("\n" + player);

            System.out.print("Pins\t\t");
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
                System.out.print("\t");
            }
            System.out.println();
        });
        System.out.println("\n('X') Strike, ('/') Spare, ('F') Fault, ('E') Error");
        System.out.println("\n\nPress Enter key to continue...");
        System.in.read();
    }
}
