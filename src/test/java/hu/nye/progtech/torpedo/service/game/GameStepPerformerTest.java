package hu.nye.progtech.torpedo.service.game;

import hu.nye.progtech.torpedo.service.input.UserInteraction;
import hu.nye.progtech.torpedo.service.moves.PlayerMoves;
import hu.nye.progtech.torpedo.service.validators.MapValidators;
import hu.nye.progtech.torpedo.ui.MapPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameStepPerformerTest {
    private static final String INPUT = "input";

    @Mock
    private UserInteraction userInteraction;
    @Mock
    private MapPrinter mapPrinter;
    @Mock
    private MapValidators mapValidators;
    @Mock
    private PlayerMoves playerMoves;

    private GameStepPerformer underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GameStepPerformer( userInteraction);
    }

    @Test
    void performGameStep() {
        // given
        given(userInteraction.readInput()).willReturn(INPUT);

        // when
        underTest.performGameStep();

        // then
        verify(userInteraction).readInput();

    }
}