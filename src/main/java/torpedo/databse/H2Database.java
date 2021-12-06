package torpedo.databse;

import torpedo.model.Players;

import javax.management.Query;
import java.sql.*;

public class H2Database {

    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:~/progtech";

    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public Connection conn;
    public Statement statement;

    public H2Database() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Kapcsolódás az adatbázisra...");
            this.conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            System.out.println("Sikeres kapcsolódás az adatbázisra");

        } catch (SQLException | ClassNotFoundException se) {
            System.out.println("Hiba történt az adatbáis kapcsolódás során: " + se);
        }
    }

    public boolean createTable(String sql) {
        System.out.println("Tábla létrehozása folyamatban...");
        try {
            statement = conn.createStatement();
            statement.executeUpdate(sql);

            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Sikertelen művelet: " + throwables);
            return false;
        }
        return true;
    }

    public void insertData(String sql, String playerName) {
        System.out.println("Adatok beszúra folyamatban...");
        try {
            statement = conn.createStatement();

            //statement.execute("INSERT INTO game_saves(name,wins)" + "VALUES(?,?)");
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, playerName);
            stmt.setInt(2,1);
            stmt.execute();
            stmt.close();

           statement.close();
        } catch (SQLException throwables) {
            System.out.println("Sikertelen művelet: " + throwables);

        }

    }

    public String selectUsers(String sql) {
        String name = "";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                System.out.print("Name: " + result.getString("name"));
                name = result.getString("name");
                System.out.println(", Wins: " + result.getInt("wins"));
            }
        } catch (SQLException throwables) {
            System.out.println("Sikertelen művelet: " + throwables);
        }
        return name;
    }

    public boolean updateData(String sql) {
        System.out.println("Adatok frissítése folyamatban...");
        try {
            statement = conn.createStatement();
            statement.execute(sql);

            statement.close();
        } catch (SQLException throwables) {
            System.out.println("Sikertelen művelet: " + throwables);
            return false;
        }
        return true;
    }



}
