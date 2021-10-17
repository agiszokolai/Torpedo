package torpedo.model;

import java.text.MessageFormat;
import java.util.List;

public class MapVO {
/**
 * A játékterület X és Y tengelyének meghatározása és a térkép kiíratása
 * */
    public static final List<String> MAP_X_DESCRIPTIONS = List.of("A","B","C","D","E","F");
    public static final List<String> MAP_Y_DESCRIPTIONS = List.of("1","2","3","4","5","6");
    private final int map_x_size;
    private final int map_y_size;

    public MapVO(int x_size, int y_size){
        this.map_x_size = x_size;
        this.map_y_size = y_size;
    }

    public void getMap(String[][] map){

        for (String desc: MAP_X_DESCRIPTIONS) {
            System.out.print("\t" + desc + " ");
        }

        System.out.println("\n  _________________________");
        for (int i = 0; i < map.length; i++) {
            System.out.print(MAP_Y_DESCRIPTIONS.get(i) + " | ");

            for (int j = 0; j < map[i].length; j++) {
                System.out.print(MessageFormat.format("{0} | ", map[i][j] == null ? " " : map[i][j]));
            }

            System.out.println();
        }
        System.out.println("  _________________________");
    }

    @Override
    public String toString() {
        return "MapVO{" +
                "map_x_size=" + map_x_size +
                ", map_y_size=" + map_y_size +
                '}';
    }
}
