package torpedo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import torpedo.model.Players;
import torpedo.model.Ships;
import torpedo.service.game.GameController;
import torpedo.service.game.GameStepPerformer;
import torpedo.service.input.UserInteraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Configuration
public class ApplicationConfiguration {

    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:~/progtech", "sa", "");
    }

    @Bean(initMethod = "start")
    GameController gameController(GameStepPerformer gameStepPerformer, Players player1, Players player2) {
        return new GameController(gameStepPerformer, player1, player2);
    }

    @Bean
    Players player1() {
        return new Players(6, 6, new Ships());
    }

    @Bean
    Players player2() {
        return new Players(6, 6, new Ships());
    }

    @Bean
    GameStepPerformer gameStepPerformer(UserInteraction userInteraction) {
        return new GameStepPerformer(userInteraction);
    }

    @Bean
    UserInteraction userInteraction() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        return new UserInteraction(bufferedReader);
    }
}
