import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        ArrayList<BoardGame> boardGames = new ArrayList<>();
        boardGames.add(new BoardGame("Squidward", 2, 4, "Kids"));
        boardGames.add(new BoardGame("Duece", 2, 10, "Cards"));
        boardGames.add(new BoardGame("Skittles", 2, 4, "Kids"));
        System.out.println(boardGames);
        // 2. Add three BoardGames to the new list.
        // 3. Print the new list.
        // 4. Add items in the new list to `games` with the `addAll` method.
        games.addAll(boardGames);
        // 5. Print `games`.
        System.out.println(games);
    }
}
