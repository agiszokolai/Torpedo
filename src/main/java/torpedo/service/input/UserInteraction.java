package torpedo.service.input;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.model.Ships;
import torpedo.service.validators.MapValidators;

import java.io.BufferedReader;
import java.io.IOException;

public class UserInteraction {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInteraction.class);

    private final BufferedReader reader;
    private final MapValidators mapValidators = new MapValidators();

    public UserInteraction(BufferedReader reader) {
        this.reader = reader;
    }

    //játékos nevének bekérése
    public String readPlayerName(){
        LOGGER.info("Kérem adja meg a játékos nevét: ");
        return readInput();
    }

    // a hajó helyének bekérése
    public String readPlayerShips(Ships ships){
        LOGGER.info("Kérem adja meg, hogy melyik helyre kíván hajót elhelyezni, pl: A1, C5, A1-A3");
        while(ships.isLeftShips()){
            LOGGER.info("Hajó helye:");
            String ship_place = readInput();

        }

        return  "";
    }

    public String readInput(){

        String input = null;
        try {
            input = reader.readLine();
        }catch (IOException e){
            LOGGER.error("Hiba történt az adatok beolvasása során", e);
        }

        return  input;
    }


}
