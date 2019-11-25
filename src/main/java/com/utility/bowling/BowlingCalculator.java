package com.utility.bowling;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingFrameService;
import com.utility.bowling.service.BowlingParserService;
import com.utility.bowling.service.BowlingPrintService;
import com.utility.bowling.service.BowlingScoreService;
import com.utility.bowling.service.impl.BowlingFrameServiceImpl;
import com.utility.bowling.service.impl.BowlingParserServiceImpl;
import com.utility.bowling.service.impl.BowlingPrintServiceImpl;
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
        BowlingPrintService bowlingPrintService = new BowlingPrintServiceImpl();

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


        bowlingPrintService.initUI();
        playerFrames.forEach((bowlingPrintService::printPlayerScore));
        bowlingPrintService.printFooter();

        System.out.print("\n\n\n\nPress Enter key to continue...\n");
        System.in.read();
    }
}
