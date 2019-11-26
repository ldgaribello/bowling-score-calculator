package com.utility.bowling.service.impl;

import com.utility.bowling.model.AttemptPins;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingScoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BowlingScoreServiceImplTest {

    private BowlingScoreService bowlingScoreService = new BowlingScoreServiceImpl();

    @Test
    public void testProcessStrikeFrame() {
        List<PlayerFrame> frames = getStrikeFrame();
        bowlingScoreService.processPlayerFrames(frames);
        assertThat(frames.get(0).getFrameScore().getScore()).isEqualTo(10);
        assertThat(frames.get(0).getFrameScore().getScoreType()).isEqualTo("X");
    }

    @Test
    public void testProcessTwoStrikeFramesInARow() {
        List<PlayerFrame> frames = getTwoConsecutiveStrikeFrames();
        bowlingScoreService.processPlayerFrames(frames);
        assertThat(frames.get(0).getFrameScore().getScore()).isEqualTo(20);
        assertThat(frames.get(0).getFrameScore().getScoreType()).isEqualTo("X");
        assertThat(frames.get(1).getFrameScore().getScore()).isEqualTo(10);
        assertThat(frames.get(1).getFrameScore().getScoreType()).isEqualTo("X");
    }

    @Test
    public void testProcessThreeStrikeFramesInARow() {
        List<PlayerFrame> frames = getThreeConsecutiveStrikeFrames();
        bowlingScoreService.processPlayerFrames(frames);
        assertThat(frames.get(0).getFrameScore().getScore()).isEqualTo(30);
        assertThat(frames.get(0).getFrameScore().getScoreType()).isEqualTo("X");
        assertThat(frames.get(1).getFrameScore().getScore()).isEqualTo(20);
        assertThat(frames.get(1).getFrameScore().getScoreType()).isEqualTo("X");
        assertThat(frames.get(2).getFrameScore().getScore()).isEqualTo(10);
        assertThat(frames.get(2).getFrameScore().getScoreType()).isEqualTo("X");
    }

    @Test
    public void testProcessSplitFrame() {
        List<PlayerFrame> frames = getSplitFrame();
        bowlingScoreService.processPlayerFrames(frames);
        assertThat(frames.get(0).getFrameScore().getScore()).isEqualTo(10);
        assertThat(frames.get(0).getFrameScore().getScoreType()).isEqualTo("/");
    }

    private List<PlayerFrame> getStrikeFrame() {
        AttemptPins attemptPins1 = new AttemptPins();
        attemptPins1.setPins(10);
        attemptPins1.setSymbol("X");

        PlayerFrame playerFrame1 = new PlayerFrame(1);
        playerFrame1.setAttemptPins1(attemptPins1);

        return Collections.singletonList(
                playerFrame1
        );
    }

    private List<PlayerFrame> getTwoConsecutiveStrikeFrames() {
        AttemptPins attemptPins1 = new AttemptPins();
        attemptPins1.setPins(10);
        attemptPins1.setSymbol("X");
        PlayerFrame playerFrame1 = new PlayerFrame(1);
        playerFrame1.setAttemptPins1(attemptPins1);

        AttemptPins attemptPins2 = new AttemptPins();
        attemptPins2.setPins(10);
        attemptPins2.setSymbol("X");
        PlayerFrame playerFrame2 = new PlayerFrame(2);
        playerFrame2.setAttemptPins1(attemptPins2);

        return Arrays.asList(
                playerFrame1,
                playerFrame2
        );
    }

    private List<PlayerFrame> getThreeConsecutiveStrikeFrames() {
        AttemptPins attemptPins1 = new AttemptPins();
        attemptPins1.setPins(10);
        attemptPins1.setSymbol("X");
        PlayerFrame playerFrame1 = new PlayerFrame(1);
        playerFrame1.setAttemptPins1(attemptPins1);

        AttemptPins attemptPins2 = new AttemptPins();
        attemptPins2.setPins(10);
        attemptPins2.setSymbol("X");
        PlayerFrame playerFrame2 = new PlayerFrame(2);
        playerFrame2.setAttemptPins1(attemptPins2);

        AttemptPins attemptPins3 = new AttemptPins();
        attemptPins3.setPins(10);
        attemptPins3.setSymbol("X");
        PlayerFrame playerFrame3 = new PlayerFrame(2);
        playerFrame3.setAttemptPins1(attemptPins3);

        return Arrays.asList(
                playerFrame1,
                playerFrame2,
                playerFrame3
        );
    }

    private List<PlayerFrame> getSplitFrame() {
        AttemptPins attemptPins1 = new AttemptPins();
        attemptPins1.setPins(5);
        attemptPins1.setSymbol("5");
        AttemptPins attemptPins2 = new AttemptPins();
        attemptPins2.setPins(5);
        attemptPins2.setSymbol("/");

        PlayerFrame playerFrame1 = new PlayerFrame(1);
        playerFrame1.setAttemptPins1(attemptPins1);
        playerFrame1.setAttemptPins2(attemptPins2);

        return Collections.singletonList(
                playerFrame1
        );
    }
}
