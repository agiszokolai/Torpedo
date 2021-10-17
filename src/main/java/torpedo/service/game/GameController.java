package torpedo.service.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.GameState;
import torpedo.model.Players;
import torpedo.service.util.MapUtil;

public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    public GameStepPerformer gameStepPerformer;
    public MapUtil mapUtil;
    public GameState gameState;

    public GameController(GameStepPerformer gameStepPerformer, MapUtil mapUtil, GameState gameState) {
        this.gameStepPerformer = gameStepPerformer;
        this.mapUtil = mapUtil;
        this.gameState = gameState;
    }

    public void start(){
        LOGGER.info("Játék elkezdődöt");
        while (isGameInProgress()) {
            gameStepPerformer.performGameStep();
        }
        LOGGER.info("Játék vége!");
    }

    private boolean isGameInProgress(){
        return false;
    }

}
