import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise12 {

    public static void main(String[] args) {
        // NEEDS WORK
        ArrayList<BoardGame> games = GameRepository.getAll();

//        for (int i = 0 ; i < games.size(); i++){
//            games += -2;
//        }

        // 1. Shift all games two positions to the left. A game at index 0 "shifts" to the end of the list.
        // Example: A,B,C,D,E -> shifted two positions is -> C,D,E,A,B
        // 2. Print `games` and confirm the new order.
    }
}
