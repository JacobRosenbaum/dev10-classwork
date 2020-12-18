import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        games.add(new BoardGame("Spongebob", 2, 4, "Kids"));
        System.out.println();
        games.add(new BoardGame("Uno", 2, 10, "Cards"));
        System.out.println(games);

        games.add(new BoardGame("Candy Land", 2, 4, "Kids"));
        System.out.println(games);


        // 1. Add three new games to `games` with the `add` method.
        // 2. Print `games` after each add.
    }
}
