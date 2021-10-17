package torpedo.service.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import torpedo.service.input.UserInteraction;

public class GameStepPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameStepPerformer.class);

    private final UserInteraction userInteraction;

    public GameStepPerformer(UserInteraction userInteraction) {
        this.userInteraction = userInteraction;
    }

   public void performGameStep(){
        String input = userInteraction.readInput();
        LOGGER.info("Felhasználói interakció = '{}'", input);
   }
}
