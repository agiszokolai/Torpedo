package hu.nye.progtech.torpedo.service.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hu.nye.progtech.torpedo.model.Players;
import hu.nye.progtech.torpedo.service.input.UserInteraction;
import hu.nye.progtech.torpedo.service.moves.PlayerMoves;
import hu.nye.progtech.torpedo.service.validators.MapValidators;
import hu.nye.progtech.torpedo.ui.MapPrinter;

/**
 * Vezérli a játék lépéseit
 */
public class GameStepPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    private final UserInteraction userInteraction;
    private final MapPrinter mapPrinter = new MapPrinter();
    private final MapValidators mapValidators = new MapValidators();
    private final PlayerMoves playerMoves = new PlayerMoves();

    public GameStepPerformer(UserInteraction userInteraction) {
        this.userInteraction = userInteraction;
    }

    public void readPlayerName(Players player) {
        player.setName(userInteraction.readPlayerName());
    }

    // a hajó helyének bekérése
    public void placementShipsForPlayer(Players player) {
        LOGGER.info("Kerem adja meg, hogy melyik helyre kivan hajot elhelyezni, pl: A1, C5, A1-A3");

        while (player.ships.isLeftShips()) {
            LOGGER.info(player.ships.toString());

            mapPrinter.printPlayerMapShips(player.map_ships);

            LOGGER.info("{} hajojanak helye:", player.getName());
            String ship_place = userInteraction.readInput();

            if (mapValidators.isValidShipPlace(ship_place, player.map_ships) && player.ships.isShipAvailable(player.ships.getShipSizeByShipPlace(ship_place))) {
                playerMoves.placeShipForPlayer(player, ship_place);
                player.ships.setLeft_ships(player.ships.getLeft_ships() - 1);
                System.out.println("Van: " + player.ships.getLeft_ships());

            } else {
                LOGGER.info("A megadott hely mar foglalt vagy nem megfelelo formatum!!");
            }
        }

        LOGGER.info("{} vegleges mapja", player.getName());
        mapPrinter.printPlayerMapShips(player.map_ships);
    }

    private boolean isTipHit(Players player1, Players player2) {
        LOGGER.info(player1.getName() + " jatekos palyai");
        mapPrinter.printPlayerMaps(player1.map_ships, player1.map_moves);

        String tip = tips(player1);

        int x_coor = player1.ships.getShipYCoordinate(tip);
        int y_coor = player1.ships.getShipXCoordinate(tip);

        if (playerMoves.playerMove(player1, tip, player2)) {

            player2.map_ships[x_coor][y_coor] = "X";

            if (player2.ships.isShipSunk(tip, player2.map_ships)) {
                LOGGER.info("Talalt sullyedt!");
            } else {
                LOGGER.info("Talalt!");
            }
            player1.map_moves[x_coor][y_coor] = "+";
            return true;
        }

        LOGGER.info("Nem talalt!");
        player1.map_moves[x_coor][y_coor] = "X";

        return false;
    }

    public void shipBattle(Players player1, Players player2) {

        boolean is_hit;

        do {
            is_hit = isTipHit(player1, player2);
        } while (is_hit && player2.ships.aliveShipCount(player2.map_ships) > 0);

        is_hit = false;

        if (player2.ships.aliveShipCount(player2.map_ships) > 0) {
            do {
                is_hit = isTipHit(player2, player1);
            } while (is_hit && player1.ships.aliveShipCount(player1.map_ships) > 0);
        }
    }

    public void performGameStep() {
        String input = userInteraction.readInput();
        LOGGER.info("Felhasznaloi interakcio = '{}'", input);
    }

    private String tips(Players player) {
        String ship_place;
        do {
            LOGGER.info("{} tipje:", player.getName());
            ship_place = userInteraction.readInput();
        } while (ship_place.length() != 2 || !mapValidators.isValidMovePlace(ship_place));

        return ship_place;
    }
}
