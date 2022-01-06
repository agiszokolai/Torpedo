package hu.nye.progtech.torpedo.model;

public class Players {
    /**
     * Players osztály a játékos(ok) számára. A játékosokhoz hozzárendeljük a Ships osztályt.
     */
    public String[][] map_ships;
    public String[][] map_moves;
    public String name;
    public Ships ships;
    public int wins =  0;

    public Players(int x_size, int y_size, Ships ships) {
        this.map_ships = new String[x_size][y_size];
        this.map_moves = new String[x_size][y_size];
        this.ships = ships;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[][] getMap_ships() {
        return map_ships;
    }

    public void setMap_ships(String[][] map_ships) {
        this.map_ships = map_ships;
    }

    public String[][] getMap_moves() {
        return map_moves;
    }

    public void setMap_moves(String[][] map_moves) {
        this.map_moves = map_moves;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    @Override
    public String toString() {
        return "";
    }


}
