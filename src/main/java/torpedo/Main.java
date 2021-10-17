package torpedo;

import org.slf4j.LoggerFactory;
import torpedo.model.MapVO;
import torpedo.model.Players;
import torpedo.model.Ships;
import torpedo.service.input.UserInteraction;
import torpedo.service.validators.MapValidators;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Logger;

public class Main {

    static int x_size = 6;
    static int y_size = 6;

    public static void main(String[] args) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInteraction userInteraction = new UserInteraction(bufferedReader);

        Players player_user = new Players(x_size, y_size, new Ships(), userInteraction.readPlayerName());
        Players player_computer = new Players(x_size, y_size, new Ships(), "Gép");

        MapValidators mapValidators = new MapValidators();
        String test = "A3-A2";

        userInteraction.readPlayerShips(player_user);
        System.out.println(mapValidators.isValidShipPlace(test, player_user.map_ships));
    }
}
