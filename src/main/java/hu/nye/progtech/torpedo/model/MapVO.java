package hu.nye.progtech.torpedo.model;

import java.util.List;

public class MapVO {
    /**
     * A játékterület X és Y tengelyének meghatározása és a térkép kiíratása
     */
    public static final List<String> MAP_X_DESCRIPTIONS = List.of("A", "B", "C", "D", "E", "F");
    public static final List<String> MAP_Y_DESCRIPTIONS = List.of("1", "2", "3", "4", "5", "6");
    private final int map_x_size;
    private final int map_y_size;

    public MapVO(int x_size, int y_size) {
        this.map_x_size = x_size;
        this.map_y_size = y_size;
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "map_x_size=" + map_x_size +
                ", map_y_size=" + map_y_size +
                '}';
    }
}
