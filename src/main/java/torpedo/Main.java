package torpedo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import torpedo.databse.H2Database;
import torpedo.model.Players;
import torpedo.model.Ships;
import torpedo.service.game.GameController;
import torpedo.service.game.GameStepPerformer;
import torpedo.service.input.UserInteraction;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    static int x_size = 6;
    static int y_size = 6;

    public static void main(String[] args) {
        //spring
         ApplicationContext context = new AnnotationConfigApplicationContext("torpedo");
        /*GameController gameController = context.getBean(GameController.class);
        gameController.start();*/


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        UserInteraction userInteraction = new UserInteraction(bufferedReader);

        Players player_user = new Players(x_size, y_size, new Ships());
        Players player_user2 = new Players(x_size, y_size, new Ships());

        GameStepPerformer gameStepPerformer = new GameStepPerformer(userInteraction);
        GameController gameController = new GameController(gameStepPerformer, player_user, player_user2);


        H2Database h2Database = new H2Database();
       /* h2Database.createTable("CREATE OR REPLACE TABLE game_saves (\n" +
                "    id int not null AUTO_INCREMENT PRIMARY KEY,\n" +
                "    name varchar(255) not null,\n" +
                "    wins int\n" +
                ")");*/

        h2Database.insertData("INSERT INTO game_saves(name,wins)" + "VALUES(?,?)", player_user.getName());
        h2Database.selectUsers("SELECT NAME ,WINS FROM game_saves NAME " );

        gameController.start();
    }
}
