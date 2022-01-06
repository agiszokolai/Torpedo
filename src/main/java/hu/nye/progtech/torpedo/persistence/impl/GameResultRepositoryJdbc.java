package hu.nye.progtech.torpedo.persistence.impl;

import hu.nye.progtech.torpedo.model.Players;
import hu.nye.progtech.torpedo.persistence.GameResultRepository;
import hu.nye.progtech.torpedo.service.game.GameController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameResultRepositoryJdbc implements GameResultRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(GameResultRepositoryJdbc.class);
    private static final Logger LOGGER2 = LoggerFactory.getLogger("GameResultRepositoryJdbc.class");

    Connection connection ;
    public GameController gameController;

    static final String INSERT_STATEMENT = "INSERT INTO game_result (NAME, WINS) VALUES (?, ?);";
    static final String UPDATE_STATEMENT = "UPDATE game_result SET WINS = WINS + ? WHERE NAME = ?;";
    static final String SELECT_STATEMENT = "SELECT * FROM game_result";
    static final String SELECTORDER_STATEMENT = "SELECT * FROM game_result ORDER BY WINS DESC;";

    private List<Players> players;

    public GameResultRepositoryJdbc(Connection connection, GameController gamecontroller) throws SQLException {
        players = new ArrayList<>();
        this.connection = connection;
    }

    public void close() throws Exception {
        connection.close();
    }

    public void insertOrUpdate(Players players){
        try {
            if (playerIsExist(players)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATEMENT)) {
                    preparedStatement.setString(1, players.getName());

                    preparedStatement.setInt(2, players.getWins());
                    preparedStatement.executeUpdate();
                }
            } else {
                try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATEMENT)) {
                    preparedStatement.setString(1, players.getName());
                    System.out.println(players.getName());
                    preparedStatement.setInt(2, players.getWins());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean playerIsExist(Players players){
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATEMENT)) {
            preparedStatement.setString(1, players.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            if (count == 1) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Players> load() {
        try{
            highScore();
            return players;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void highScore() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(SELECTORDER_STATEMENT);
        int id = 1;
        while (resultSet.next()) {

            String name = resultSet.getString("NAME");
            int win = Integer.parseInt(resultSet.getString("WINS"));
            System.out.println(id+". " + name + win);
            id++;
        }
    }

}
