package com.utility.bowling;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.service.BowlingParserService;
import com.utility.bowling.service.BowlingParserServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BowlingCalculator {

    public static void main(String[] args) throws Exception {
        BowlingParserService bowlingParserService = new BowlingParserServiceImpl();

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

        Map<String, List<PlayerAttempt>> playerAttempts = bowlingParserService.parseScoreLines(lines);

        playerAttempts.forEach((player, attempts) -> {
            System.out.println("\n" + player);
            for (PlayerAttempt attempt : attempts) {
                System.out.print(attempt.getAttemptScore() + "\t");
            }
            System.out.println();
        });

        System.out.println("\n\n");
        System.out.println("Press Enter key to continue...");
        System.in.read();
    }
}
