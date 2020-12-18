import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise06 {

    public static void main(String[] args) {
        // NEEDS WORK
        ArrayList<BoardGame> games = GameRepository.getAll();
        for (BoardGame maxPlayerGames : games){
            if (maxPlayerGames.getMaxPlayers() > maxPlayerGames.getMaxPlayers()-1){
                System.out.println(maxPlayerGames);
            }
        }
        // 1. Use a loop to find the game in `games` that can be played by the most players.
        // 2. Print the game. (Expected: "Ultimate Werewolf...")
    }
}

