import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise10 {

    public static void main(String[] args) {
        //NEEDS WORK
        ArrayList<BoardGame> games = GameRepository.getAll();
        for (BoardGame boardGame : games){
            if (boardGame.getMinPlayers() == 1){
                games.remove(boardGame.getMinPlayers() == 1);
            }
        }
        System.out.println(games);
        // 1. Loop over `games` and remove any game that can be played by one person.
        // 2. Print `games` and confirm single-player games are removed.
    }
}
