package torpedo.service.moves;

import torpedo.model.MapVO;
import torpedo.model.Players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PlayerMoves {

    public PlayerMoves() {
    }

    public boolean playerMove(Players player1, String ship_place, Players player2) {

        int x = MapVO.MAP_X_DESCRIPTIONS.indexOf(ship_place.substring(0, 1).toUpperCase());
        int y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(ship_place.substring(1));

        player1.map_moves[y][x] = player2.map_ships[y][x] == null ? "X" : "+";

        return player1.map_moves[y][x].equals("+");
    }

    public void placeShipForPlayer(Players player, String ship_place) {

        List<String> data = getShipListByShipPlace(ship_place);

        for (String d : data) {
            int x = MapVO.MAP_X_DESCRIPTIONS.indexOf(d.substring(0, 1).toUpperCase());
            int y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(d.substring(1));

            player.map_ships[y][x] = "+";
        }
    }

    private List<String> getShipListByShipPlace(String ship_place) {

        List<String> data = new ArrayList<>(Arrays.asList(ship_place.split("-")));

        if (data.size() == 2) {

            String first_x_str = data.get(0).substring(0, 1).toUpperCase();
            String second_x_str = data.get(1).substring(0, 1).toUpperCase();

            int first_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(first_x_str);
            int first_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(data.get(0).substring(1, 2));

            int second_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(second_x_str);
            int second_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(data.get(1).substring(1, 2));

            int max_x = Math.max(first_x, second_x);
            int max_y = Math.max(first_y, second_y);

            int difference_x = Math.abs(second_x - first_x);
            int difference_y = Math.abs(second_y - first_y);

            if (difference_x == 2 || difference_y == 2) {

                if (difference_x == 0) {
                    String new_element = MapVO.MAP_X_DESCRIPTIONS.get(first_x) + MapVO.MAP_Y_DESCRIPTIONS.get(max_y - 1);
                    data.add(new_element);
                } else {
                    String new_element = MapVO.MAP_X_DESCRIPTIONS.get(max_x - 1) + MapVO.MAP_Y_DESCRIPTIONS.get(first_y);
                    data.add(new_element);
                }
            }
        }

        return data;
    }
}

