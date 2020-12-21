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
        ArrayList<Stone> stoneCounter = new ArrayList<>();
        String dash = " _ ";
        // PRINT COLUMNS
        for (int i = 0; i <= board.length; i++) {
            if (i == 0) {
                System.out.print("  ");
            } else {
                System.out.printf("%s ", (i < 10) ? "0" + i : i);
            }
        }
        System.out.println();
        System.out.print("01");
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
//                if ((col == stone.getColumn()) && (row == stone.getRow())) {
//                    System.out.print(" " + symbol.getSymbol() + " ");
//                }
                for (int stones = 0 ; stones < currentPlayer.getStones().size(); stones++) {
                    stoneCounter.add(currentPlayer.getStones().get(stones));
                    if (row == (stoneCounter.get(stones).getRow() - 1) &&
                            col == (stoneCounter.get(stones).getColumn() - 1) &&
                            (stoneCounter.get(stones).isBlack() == true)
                    ) {
                        System.out.print(" " + "X" + " ");
                        col++;
                    } else if (row == (stoneCounter.get(stones).getRow() - 1) &&
                            col == (stoneCounter.get(stones).getColumn() - 1) &&
                            (stoneCounter.get(stones).isBlack() == false)
                    ) {
                        System.out.print(" " + "O" + " ");
                        col++;
                    }
                }
//                for (Stone stones : stoneCounter) {
//                    stoneCounter.add(stones);
//                }
//                for (Stone markStones : stoneCounter){
//                    if ((row == (markStones.getRow() - 1)) &&
//                            (col == (markStones.getColumn() - 1)) &&
//                            (markStones.isBlack() == true)
//                    ) {
//                        System.out.print(" " + "X" + " ");
//                        col++;
//                    } else if ((row == markStones.getRow() - 1) &&
//                            (col == (markStones.getColumn() - 1)) &&
//                            (markStones.isBlack() == false)
//                    ) {
//                        System.out.print(" " + "O" + " ");
//                        col++;
////                    }
//                    }
//                }

                // PRINT DASHES
                if (board[row][col] == '\u0000') {
                    System.out.print(dash);
                }
            }
            // PRINT ROWS
            System.out.println();
            if (row + counter < board.length + 1) {
                System.out.printf("%-2s", ((row + counter) < 10) ? "0" + (row + counter) : (row + counter));
            }
        }

    }
}
