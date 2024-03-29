import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise09 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Grab the 8th game in `games`.
        BoardGame seven = games.get(7);
        // 2. Remove it passing its reference to the `remove` method

        games.remove(seven); // <-- It works!
        System.out.println(games);
        // 3. Print `games` and confirm it's gone.
    }
}
