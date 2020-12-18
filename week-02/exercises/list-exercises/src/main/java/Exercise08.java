import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise08 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();
        games.remove(4);
        games.remove(9);

        System.out.println(games);
        // 1. Remove the the 5th and 10th game from `games`.
        // 2. Print `games`.
    }
}
