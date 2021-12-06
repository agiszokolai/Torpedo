package torpedo.configuration;

import org.springframework.context.annotation.Bean;
import torpedo.model.GameState;
import torpedo.model.Players;
import torpedo.service.game.GameController;
import torpedo.service.game.GameStepPerformer;
import torpedo.service.input.UserInteraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationConfiguration {
    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/progtech", "sa", "");
    }
    @Bean
    public GameController gameController(GameStepPerformer gameStepPerformer, Players player1, Players player2) {
        return new GameController(gameStepPerformer, player1, player2);
    }

    @Bean
    public GameStepPerformer gameStepPerformer(UserInteraction userInteraction) {
        return new GameStepPerformer(userInteraction);
    }

    @Bean
    public UserInteraction userInteraction() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInteraction userInteraction = new UserInteraction(bufferedReader);
        return null;
    }

    @Bean
    public GameState gameState() {
        return null;
    }
}
