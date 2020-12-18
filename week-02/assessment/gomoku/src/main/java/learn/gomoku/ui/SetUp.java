package learn.gomoku.ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class SetUp {
    Scanner console = new Scanner(System.in);
    public void startGame() {

            System.out.print("Welcome to Gomoku\n================\n\n");
            Player player1 = setPlayer1(console);
            Player player2 = setPlayer2(console);
            Gomoku currentPlayer = new Gomoku(player1,player2);
            System.out.println("\nRandomizing...\n");
            System.out.println(currentPlayer.getCurrent().getName() + " goes first.");
    }

    public Player setPlayer1(Scanner console) {
        System.out.println("Player 1 is: \n");
        System.out.print("1. Human\n2. Random Player\nSelect [1-2]: ");
        String player = console.nextLine();
        if (player.equals("1")) {
            System.out.print("\nPlayer 1, printith thou name: ");
            Player player1 = new HumanPlayer(console.nextLine());
            return player1;
        } else if (player.equals("2")) {
            Player player1 = new RandomPlayer();
            return player1;
        }
        return null;
    }

    public Player setPlayer2(Scanner console) {
        System.out.println("\nPlayer 2 is: \n");
        System.out.print("1. Human\n2. Random Player\nSelect [1-2]: ");
        String player = console.nextLine();
        if (player.equals("1")) {
            System.out.print("\nPlayer 1, printith thou name: ");
            Player player2 = new HumanPlayer(console.nextLine());
            return player2;
        } else if (player.equals("2")) {
            Player player2 = new RandomPlayer();
            return player2;
        }
        return null;
    }

}
