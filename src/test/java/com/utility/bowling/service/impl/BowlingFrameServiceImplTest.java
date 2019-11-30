package com.utility.bowling.service.impl;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.model.PlayerFrame;
import com.utility.bowling.service.BowlingFrameService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BowlingFrameServiceImplTest {

    private BowlingFrameService bowlingFrameService = new BowlingFrameServiceImpl();

    @Test
    public void testAggregatePinsForStrikeFrame() {
        List<PlayerFrame> playerFrames = bowlingFrameService.aggregatePins(getPlayerAttemptsStrike());
        assertThat(playerFrames).isNotNull();
        assertThat(playerFrames.get(0).getAttemptPins1().getPins()).isEqualTo(10);
        assertThat(playerFrames.get(0).getAttemptPins1().getSymbol()).isEqualTo("X");
        assertThat(playerFrames.get(0).getAttemptPins2()).isNull();
        assertThat(playerFrames.get(0).getAttemptPins3()).isNull();
    }

    @Test
    public void testAggregatePinsForSplitFrame() {
        List<PlayerFrame> playerFrames = bowlingFrameService.aggregatePins(getPlayerAttemptsSplit());
        assertThat(playerFrames).isNotNull();
        assertThat(playerFrames.get(0).getAttemptPins1().getPins()).isEqualTo(4);
        assertThat(playerFrames.get(0).getAttemptPins1().getSymbol()).isEqualTo("4");
        assertThat(playerFrames.get(0).getAttemptPins2().getPins()).isEqualTo(6);
        assertThat(playerFrames.get(0).getAttemptPins2().getSymbol()).isEqualTo("/");
        assertThat(playerFrames.get(0).getAttemptPins3()).isNull();
    }

    @Test
    public void testAggregatePinsForRegularFrame() {
        List<PlayerFrame> playerFrames = bowlingFrameService.aggregatePins(getPlayerAttemptsRegular());
        assertThat(playerFrames).isNotNull();
        assertThat(playerFrames.get(0).getAttemptPins1().getPins()).isEqualTo(4);
        assertThat(playerFrames.get(0).getAttemptPins1().getSymbol()).isEqualTo("4");
        assertThat(playerFrames.get(0).getAttemptPins2().getPins()).isEqualTo(3);
        assertThat(playerFrames.get(0).getAttemptPins2().getSymbol()).isEqualTo("3");
        assertThat(playerFrames.get(0).getAttemptPins3()).isNull();
    }

    @Test
    public void testAggregatePinsForErrorFrame() {
        List<PlayerFrame> playerFrames = bowlingFrameService.aggregatePins(getPlayerAttemptsError());
        assertThat(playerFrames).isNotNull();
        assertThat(playerFrames.get(0).getAttemptPins1().getPins()).isEqualTo(0);
        assertThat(playerFrames.get(0).getAttemptPins1().getSymbol()).isEqualTo("E");
        assertThat(playerFrames.get(0).getAttemptPins2().getPins()).isEqualTo(10);
        assertThat(playerFrames.get(0).getAttemptPins2().getSymbol()).isEqualTo("/");
        assertThat(playerFrames.get(0).getAttemptPins3()).isNull();
    }

    private List<PlayerAttempt> getPlayerAttemptsStrike() {
        PlayerAttempt attempt1 = new PlayerAttempt();
        attempt1.setAttemptScore(10);
        return Collections.singletonList(attempt1);
    }

    private List<PlayerAttempt> getPlayerAttemptsSplit() {
        PlayerAttempt attempt1 = new PlayerAttempt();
        attempt1.setAttemptScore(4);
        PlayerAttempt attempt2 = new PlayerAttempt();
        attempt2.setAttemptScore(6);
        return Arrays.asList(attempt1, attempt2);
    }

    private List<PlayerAttempt> getPlayerAttemptsRegular() {
        PlayerAttempt attempt1 = new PlayerAttempt();
        attempt1.setAttemptScore(4);
        PlayerAttempt attempt2 = new PlayerAttempt();
        attempt2.setAttemptScore(3);
        return Arrays.asList(attempt1, attempt2);
    }

    private List<PlayerAttempt> getPlayerAttemptsError() {
        PlayerAttempt attempt1 = new PlayerAttempt();
        attempt1.setError(true);
        PlayerAttempt attempt2 = new PlayerAttempt();
        attempt2.setAttemptScore(10);
        return Arrays.asList(attempt1, attempt2);
    }
}
