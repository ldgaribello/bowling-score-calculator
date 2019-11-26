package com.utility.bowling.service.impl;

import com.utility.bowling.model.PlayerAttempt;
import com.utility.bowling.service.BowlingParserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class BowlingParserServiceImplTest {

    private BowlingParserService bowlingParserService = new BowlingParserServiceImpl();

    @Test
    public void testParsePerfectGame() {
        Map<String, List<PlayerAttempt>> gameAttemptsByPlayer = bowlingParserService.parseScoreLines(getPerfectGame());
        assertThat(gameAttemptsByPlayer).isNotNull();
        assertThat(gameAttemptsByPlayer.get("Carl")).hasSize(10);
        assertThat(gameAttemptsByPlayer.get("Carl").get(0).getAttemptScore()).isEqualTo(10);
        assertThat(gameAttemptsByPlayer.get("Carl").get(9).getAttemptScore()).isEqualTo(10);
    }

    @Test
    public void testParseTwoPlayersGame() {
        Map<String, List<PlayerAttempt>> gameAttemptsByPlayer = bowlingParserService.parseScoreLines(getTwoPlayersGame());
        assertThat(gameAttemptsByPlayer).isNotNull();
        assertThat(gameAttemptsByPlayer.get("Jeff")).isNotNull();
        assertThat(gameAttemptsByPlayer.get("Jeff").get(0).getAttemptScore()).isEqualTo(10);
        assertThat(gameAttemptsByPlayer.get("John")).isNotNull();
        assertThat(gameAttemptsByPlayer.get("John").get(0).getAttemptScore()).isEqualTo(3);
    }

    @Test
    public void testParseGameWithErrors() {
        Map<String, List<PlayerAttempt>> gameAttemptsByPlayer = bowlingParserService.parseScoreLines(getGameWithErrors());
        assertThat(gameAttemptsByPlayer).isNotNull();
        assertThat(gameAttemptsByPlayer.get("Jeff")).isNotNull();
        assertThat(gameAttemptsByPlayer.get("Jeff").get(0).getError()).isTrue();
        assertThat(gameAttemptsByPlayer.get("John")).isNotNull();
        assertThat(gameAttemptsByPlayer.get("John").get(2).getError()).isTrue();
    }

    private List<String> getPerfectGame() {
        return Arrays.asList(
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10",
                "Carl 10"
        );
    }

    private List<String> getTwoPlayersGame() {
        return Arrays.asList(
                "Jeff 10",
                "John 3 ",
                "John 7 ",
                "Jeff 7 ",
                "Jeff 3 ",
                "John 6 ",
                "John 3 "
        );
    }

    private List<String> getGameWithErrors() {
        return Arrays.asList(
                "Jeff -10",
                "John 3 ",
                "John 7 ",
                "Jeff 7 ",
                "Jeff 3 ",
                "John 60 ",
                "John 3 "
        );
    }
}
