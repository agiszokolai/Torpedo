package hu.nye.progtech.torpedo.configuration;

import hu.nye.progtech.torpedo.persistence.impl.GameResultRepositoryJdbc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import hu.nye.progtech.torpedo.model.Players;
import hu.nye.progtech.torpedo.model.Ships;
import hu.nye.progtech.torpedo.service.game.GameController;
import hu.nye.progtech.torpedo.service.game.GameStepPerformer;
import hu.nye.progtech.torpedo.service.input.UserInteraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Configuration
public class ApplicationConfiguration {

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

    @Bean
    Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:./progtech", "sa", "");
    }
    @Bean
    GameResultRepositoryJdbc gameResultRepositoryJdbc(Connection connection, GameController gamecontroller) throws SQLException {
        return new GameResultRepositoryJdbc(connection, gamecontroller);
    }

}
