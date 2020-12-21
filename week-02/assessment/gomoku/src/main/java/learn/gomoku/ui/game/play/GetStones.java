package learn.gomoku.ui.game.play;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;

import java.util.Scanner;

public class GetStones {
    Scanner console = new Scanner(System.in);

    public Stone getBlackStone(Stone blackStoneMove, Gomoku currentPlayer) {
        Stone blackStone = null;
        if (currentPlayer.getCurrent().generateMove(currentPlayer.getStones()) == null) {
            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn");
            System.out.print("Enter a row: ");
            int row = Integer.parseInt(console.nextLine());
            System.out.print("Enter a column: ");
            int column = Integer.parseInt(console.nextLine());
            System.out.println();
            return blackStone = new Stone(row - 1, column - 1, true);
        } else {
            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn\n");
            return blackStone = blackStoneMove;
        }
    }

    public Stone getWhiteStone(Stone whiteStoneMove, Gomoku currentPlayer) {
        Stone whiteStone = null;
        if (currentPlayer.getCurrent().generateMove(currentPlayer.getStones()) == null) {
            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn");
            System.out.print("Enter a row: ");
            int row = Integer.parseInt(console.nextLine());
            System.out.print("Enter a column: ");
            int column = Integer.parseInt(console.nextLine());
            System.out.println();

            return whiteStone = new Stone(row - 1, column - 1, false);
        } else {
            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn\n");
            return whiteStone = whiteStoneMove;
        }
    }
}
