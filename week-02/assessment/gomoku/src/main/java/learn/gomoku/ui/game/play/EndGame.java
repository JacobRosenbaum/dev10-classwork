package learn.gomoku.ui.game.play;

import java.util.Scanner;

public class EndGame {

    Scanner console = new Scanner(System.in);
    SetUp playAgain = new SetUp();

    public void restartGame(){

        System.out.print("Play again? [y/n]");
        String input = console.nextLine();

        if (input.equalsIgnoreCase("y")){
            playAgain.startGame();
        }
        else{
            System.out.println("Goodbye");
        }
    }
}
