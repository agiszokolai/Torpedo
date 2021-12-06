package torpedo.service.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.MapVO;
import torpedo.model.Players;
import torpedo.service.moves.PlayerMoves;
import torpedo.service.validators.MapValidators;
import torpedo.ui.MapPrinter;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInteraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInteraction.class);

    private final BufferedReader reader;

    public UserInteraction(BufferedReader reader) {
        this.reader = reader;
    }

    //játékos nevének bekérése
    public String readPlayerName() {
        LOGGER.info("Kérem adja meg a játékos nevét: ");
        return readInput();
    }

    public String readInput() {

        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Hiba történt az adatok beolvasása során", e);
        }

        return input;
    }
}
