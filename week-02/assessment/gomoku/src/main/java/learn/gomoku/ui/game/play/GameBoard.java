package learn.gomoku.ui.game.play;

import learn.gomoku.game.Stone;
import learn.gomoku.game.Gomoku;
import learn.gomoku.players.Player;
import learn.gomoku.ui.game.item.GamePiece;

import java.util.ArrayList;
import java.util.Scanner;

public class GameBoard {

    Scanner console = new Scanner(System.in);
    int[][] board = new int[Gomoku.WIDTH][Gomoku.WIDTH];

    public void printBoard(Stone stone, GamePiece symbol, Player player1, Player player2, Gomoku currentPlayer) {
        int rowWidth = Gomoku.WIDTH;
        int colWidth = Gomoku.WIDTH;
        int[][] board = new int[rowWidth][colWidth];
        int counter = 2;
//        int stoneCounter = currentPlayer.getStones().size() - 1;
        ArrayList<Stone> stoneCounter = new ArrayList<>();
//        Stone test;


        for (int i = 0; i <= board.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.printf("%s ", (i < 10) ? "0" + i : i);
            }
        }
        System.out.println();
        System.out.print("01");
//        Stone previousStone = currentPlayer.getStones().get().getColumn();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
//                if ((col == stone.getColumn()) && (row == stone.getRow())) {
//                    System.out.print(" " + symbol.getSymbol() + " ");
//                }
                for (int stones = 0; stones < currentPlayer.getStones().size(); stones++) {
                    stoneCounter.add(currentPlayer.getStones().get(stones));
//                    System.out.println("row: " + (stoneCounter.get(stones).getRow() + 1) + " " + "column: " + (stoneCounter.get(stones).getColumn() + 1));

                    if (row == (stoneCounter.get(stones).getRow()) &&
                            col == (stoneCounter.get(stones).getColumn()) &&
                            (stoneCounter.get(stones).isBlack() == true) &&
                            (board[row][col] == '\u0000')
                    ) {
                        System.out.print(" " + "X" + " ");
                    } else if (row == (stoneCounter.get(stones).getRow()) &&
                            col == (stoneCounter.get(stones).getColumn()) &&
                            (stoneCounter.get(stones).isBlack() == false) &&
                            (board[row][col] == '\u0000')
                    ) {
                        System.out.print(" " + "O" + " ");
                    }
                }
//                else if ((row == currentPlayer.getStones().get(row).getRow()) && (col == currentPlayer.getStones().get(col).getColumn())){
//                    System.out.print(" " + symbol.getSymbol() + " ");
//                }
//                else if ((col == stoneCounter.get(col).getColumn()) && (row == stoneCounter.get(row).getRow()) && (stoneCounter.get(row).isBlack() == false)){
//                    System.out.println(" O ");
//                }
                if (board[row][col] == '\u0000' && col < colWidth) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
            if (row + counter < board.length + 1) {
                System.out.printf("%-2s", ((row + counter) < 10) ? "0" + (row + counter) : (row + counter));
            }
        }

    }
}
