package hu.nye.progtech.torpedo.service.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        LOGGER.info("Kerem adja meg a jatekos nevet: ");
        return readInput();
    }

    public String readInput() {

        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            LOGGER.error("Hiba tortent az adatok beolvasasa soran", e);
        }

        return input;
    }
}
