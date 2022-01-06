package hu.nye.progtech.torpedo.ui;

import hu.nye.progtech.torpedo.model.MapVO;

import java.text.MessageFormat;

public class MapPrinter {

    public MapPrinter() {}

    public void printPlayerMaps(String[][] map_ships, String[][] map_moves) {

        int move_row = 0;

        for (String desc : MapVO.MAP_X_DESCRIPTIONS) {
            System.out.print("\t" + desc + " ");
        }

        System.out.print("\t\t\t\t");

        for (String desc : MapVO.MAP_X_DESCRIPTIONS) {
            System.out.print("\t" + desc + " ");
        }

        System.out.println("\n  _________________________\t\t\t\t  _________________________");

        for (int i = 0; i < map_ships.length; i++) {


            for (int row = 0; row < map_ships.length; row++) {

                if (row == move_row) {
                    System.out.print(MapVO.MAP_Y_DESCRIPTIONS.get(row) + " | ");

                    for (int column = 0; column < map_ships[row].length; column++) {
                        System.out.print(MessageFormat.format("{0} | ", map_ships[row][column] == null ? " " : map_ships[row][column]));
                    }

                    System.out.print("\t\t\t");
                    System.out.print(MapVO.MAP_Y_DESCRIPTIONS.get(row) + " | ");
                    for (int column = 0; column < map_moves[row].length; column++) {
                        System.out.print(MessageFormat.format("{0} | ", map_moves[row][column] == null ? " " : map_moves[row][column]));
                    }
                }
            }
            System.out.println();
            move_row++;
        }
        System.out.println("  _________________________\t\t\t\t  _________________________");
    }

    public void printPlayerMapShips(String[][] map) {

        for (String desc : MapVO.MAP_X_DESCRIPTIONS) {
            System.out.print("\t" + desc + " ");
        }

        System.out.println("\n  _________________________");
        for (int i = 0; i < map.length; i++) {
            System.out.print(MapVO.MAP_Y_DESCRIPTIONS.get(i) + " | ");

            for (int j = 0; j < map[i].length; j++) {
                System.out.print(MessageFormat.format("{0} | ", map[i][j] == null ? " " : map[i][j]));
            }

            System.out.println();
        }
        System.out.println("  _________________________");
    }
}
