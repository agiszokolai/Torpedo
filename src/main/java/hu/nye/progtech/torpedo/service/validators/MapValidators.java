package hu.nye.progtech.torpedo.service.validators;

import hu.nye.progtech.torpedo.model.MapVO;

import java.util.ArrayList;
import java.util.Arrays;

public class MapValidators {

    public MapValidators() {
    }

    //a hajó kívánt helye helyesen van-e megadva
    public boolean isValidShipPlace(String input, String[][] player_map) {

        return isValidInputFormat(input) && isValidDistance(input) && isPlaceOccupied(input, player_map);
    }

    public boolean isValidMovePlace(String input) {

        return isValidInputFormat(input) && isValidDistance(input);
    }

    // a hajó kívánt helyének a formátuma helyesen van-e megadva
    private boolean isValidInputFormat(String input) {

        if (input.length() < 2 || input.length() > 6 || Character.isDigit(input.charAt(0)) || Character.isLetter(input.charAt(1)))
            return false;

        String[] check_data = input.split("-");

        for (String data : check_data) {

            if (data.length() != 2 || !MapVO.MAP_X_DESCRIPTIONS.contains(data.substring(0, 1).toUpperCase()) || !MapVO.MAP_Y_DESCRIPTIONS.contains(data.substring(1, 2)))
                return false;
        }

        return true;
    }

    // nagyobb hajó esetén a távolság megfelelő-e
    private boolean isValidDistance(String input) {

        String[] check_data = input.split("-");

        if (check_data.length == 1) return true;

        String first_x_str = check_data[0].substring(0, 1).toUpperCase();
        String second_x_str = check_data[1].substring(0, 1).toUpperCase();

        int first_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(first_x_str);
        int first_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(check_data[0].substring(1, 2));

        int second_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(second_x_str);
        int second_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(check_data[1].substring(1, 2));

        int difference_x = Math.abs(second_x - first_x);
        int difference_y = Math.abs(second_y - first_y);

        if (first_x_str.charAt(0) == second_x_str.charAt(0) && difference_y > 0 && difference_y < 3) return true;

        return check_data[0].charAt(1) == check_data[1].charAt(1) && difference_x > 0 && difference_x < 3;
    }

    //foglalt-e a kiválasztott terület
    private boolean isPlaceOccupied(String input, String[][] player_map) {

        ArrayList<String> check_data = new ArrayList<>(Arrays.asList(input.split("-")));

        if (check_data.size() == 2) {

            String first_x_str = check_data.get(0).substring(0, 1).toUpperCase();
            String second_x_str = check_data.get(1).substring(0, 1).toUpperCase();

            int first_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(first_x_str);
            int first_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(check_data.get(0).substring(1, 2));

            int second_x = MapVO.MAP_X_DESCRIPTIONS.indexOf(second_x_str);
            int second_y = MapVO.MAP_Y_DESCRIPTIONS.indexOf(check_data.get(1).substring(1, 2));

            int max_x = Math.max(first_x, second_x);
            int max_y = Math.max(first_y, second_y);

            int difference_x = Math.abs(second_x - first_x);
            int difference_y = Math.abs(second_y - first_y);

            if (difference_x == 2 || difference_y == 2) {

                if (difference_x == 0) {
                    String new_element = MapVO.MAP_X_DESCRIPTIONS.get(first_x) + MapVO.MAP_Y_DESCRIPTIONS.get(max_y - 1);
                    check_data.add(new_element);
                } else {
                    String new_element = MapVO.MAP_X_DESCRIPTIONS.get(max_x - 1) + MapVO.MAP_Y_DESCRIPTIONS.get(first_y);
                    check_data.add(new_element);
                }
            }
        }

        for (String check_d : check_data) {
            //System.out.println(check_d);
            int y_place = MapVO.MAP_X_DESCRIPTIONS.indexOf(check_d.substring(0, 1).toUpperCase());
            int x_place = MapVO.MAP_Y_DESCRIPTIONS.indexOf(check_d.substring(1));

            // X helyek (A-B-C-D-E-F)
            for (int i = 0; i < player_map.length; i++) {

                if (x_place != i) continue;

                // Y helyek (1-2-3-4-5-6)
                for (int j = 0; j < player_map[i].length; j++) {

                    if (y_place != j) continue;

                    if (j == 0 && player_map[i][j + 1] != null) return  false;
                    else if (j > 0 && j < 5 && (player_map[i][j + 1] != null || player_map[i][j - 1] != null)) return  false;
                    else if (j == 5 && player_map[i][j - 1] != null) return  false;

                    if (i == 0 && player_map[i + 1][j] != null) return  false;
                    else if (i > 0 && i < 5 && (player_map[i + 1][j] != null || player_map[i - 1][j] != null)) return  false;
                    else if (i == 5 && player_map[i - 1][j] != null) return  false;

                    if (player_map[i][j] != null) return false;
                }
            }
        }
        return true;
    }
}
