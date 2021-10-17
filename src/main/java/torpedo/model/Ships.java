package torpedo.model;

/**
 * Ships osztály mely az elhelyezett hajók számát és méretét
 * határozza meg
 * */
public class Ships {

    // Elhelyezhető hajók száma és mérete
    public int big_ship_count = 1;
    public static final int BIG_SHIP_SIZE = 3;

    public int medium_ship_count = 2;
    public static final int MEDIUM_SHIP_SIZE = 2;

    public int small_ship_count = 3;
    public static final int SMALL_SHIP_SIZE = 1;

    public int left_ships = 6;

    public Ships() {}

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
     * @return {@code true} ha még nem sűllyedt el az összes hajó,különben {@code false}
     * */
    public boolean isLeftShips(){
        return this.left_ships > 0;
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
