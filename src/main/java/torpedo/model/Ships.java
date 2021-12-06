package torpedo.model;

/**
 * Ships osztály mely az elhelyezett hajók számát és méretét
 * határozza meg
 */
public class Ships {

    // Elhelyezhető hajók száma és mérete
    public int big_ship_count = 1;
    public static final int BIG_SHIP_SIZE = 3;

    public int medium_ship_count = 2;
    public static final int MEDIUM_SHIP_SIZE = 2;

    public int small_ship_count = 3;
    public static final int SMALL_SHIP_SIZE = 1;

    public int left_ships = 6;


    public Ships() {
    }

    public int getLeft_ships() {
        return left_ships;
    }

    public void setLeft_ships(int left_ships) {
        this.left_ships = left_ships;
    }

    public int getBig_ship_count() {
        return big_ship_count;
    }

    public void setBig_ship_count(int big_ship_count) {
        this.big_ship_count = big_ship_count;
    }

    public int getMedium_ship_count() {
        return medium_ship_count;
    }

    public void setMedium_ship_count(int medium_ship_count) {
        this.medium_ship_count = medium_ship_count;
    }

    public int getSmall_ship_count() {
        return small_ship_count;
    }

    public void setSmall_ship_count(int small_ship_count) {
        this.small_ship_count = small_ship_count;
    }

    /**
     * Megmaradt hajók vizsgálata
     *
     * @return {@code true} ha még nem sűllyedt el az összes hajó,különben {@code false}
     */
    public boolean isLeftShips() {
        return this.left_ships > 0;
    }

    public boolean isShipAvailable(int size) {

        switch (size) {
            case 1:
                if (this.small_ship_count > 0) this.small_ship_count -= 1;
                else return false;
                break;
            case 2:
                if (this.medium_ship_count > 0) this.medium_ship_count -= 1;
                else return false;
                break;

            case 3:
                if (this.big_ship_count > 0) this.big_ship_count -= 1;
                else return false;
                break;
        }
        return true;
    }

    public int getShipSizeByShipPlace(String ship_place) {
        String[] check_data = ship_place.split("-");

        if (check_data.length == 1) return 1;

        String first_x_str = check_data[0].substring(0, 1).toUpperCase();
        String second_x_str = check_data[1].substring(0, 1).toUpperCase();

        // a1-a2 f2-f4
        int first_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(first_x_str);
        int second_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(second_x_str);

        // a1-b1
        int first_y = Integer.parseInt(check_data[0].substring(1));
        int second_y = Integer.parseInt(check_data[1].substring(1));

        if (first_x == second_x) return Math.abs((first_y - second_y)) + 1;

        return Math.abs((first_x - second_x)) + 1;
    }

    public int aliveShipCount(String[][] map_ships) {
        int db = 0;

        for (String[] map_ship : map_ships) {
            for (String s : map_ship) {
                if (s == "+") {
                    db++;
                }
            }
        }
        return db;
    }

    public boolean isShipSunk(String ship_place, String[][] map_ships) {
        int ship_y_coor = getShipXCoordinate(ship_place); // oszlop
        int ship_x_coor = getShipYCoordinate(ship_place); // sor

        try {
            if (map_ships[ship_y_coor + 1][ship_x_coor].equals("+")) {
                return false;
            }
        } catch (Exception ignored) {
        }

        try {
            if (map_ships[ship_y_coor - 1][ship_x_coor].equals("+")) {
                return false;
            }
        } catch (Exception ignored) {
        }

        try {
            if (map_ships[ship_y_coor][ship_x_coor - 1].equals("+")) {
                return false;
            }
        } catch (Exception ignored) {
        }

        try {
            if (map_ships[ship_y_coor][ship_x_coor + 1].equals("+")) {
                return false;
            }
        } catch (Exception ignored) {
        }

        return true;
    }

    public int getShipXCoordinate(String ship_place) {
        return MapVO.MAP_X_DESCRIPTIONS.indexOf(ship_place.substring(0, 1).toUpperCase());
    }

    public int getShipYCoordinate(String ship_place) {
        return MapVO.MAP_Y_DESCRIPTIONS.indexOf(ship_place.substring(1).toUpperCase());
    }

    @Override
    public String toString() {
        return "Elérhető hajók: {" +
                "\n\tNagy hajók száma: " + this.big_ship_count +
                "\n\tKözepes hajók száma: " + this.medium_ship_count +
                "\n\tKis hajók száma: " + this.small_ship_count +
                "\n}";
    }
}
