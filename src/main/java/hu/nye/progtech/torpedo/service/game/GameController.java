package hu.nye.progtech.torpedo.service.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.nye.progtech.torpedo.model.Players;

public class GameController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    public Players player1;
    public Players player2;
    public GameStepPerformer gameStepPerformer;
    public int winner = 0;


    public GameController(GameStepPerformer gameStepPerformer, Players player1, Players player2) {
        this.gameStepPerformer = gameStepPerformer;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        LOGGER.info("A jatek elkezdodott");

        // Játékosok neveinek bekérése
        gameStepPerformer.readPlayerName(player1);
        gameStepPerformer.readPlayerName(player2);

        // Játékosok hajóinak bekérése
        gameStepPerformer.placementShipsForPlayer(player1);
        gameStepPerformer.placementShipsForPlayer(player2);

        while (isGameInProgress()) {
            this.gameStepPerformer.shipBattle(player1, player2);
        }
        LOGGER.info("jatek vege!");
    }

    private boolean isGameInProgress() {

        if (player1.ships.aliveShipCount(player1.map_ships) == 0) {
            LOGGER.info("{} nyert!", player2.getName());
            winner = 1;
            player2.setWins(player2.getWins()+1);
            return false;
        }

        if (player2.ships.aliveShipCount(player2.map_ships) == 0) {
            LOGGER.info("{} nyert!", player1.getName());
            winner = 2;
            player1.setWins(player1.getWins()+1);
            return false;
        }

        return true;
    }

}
