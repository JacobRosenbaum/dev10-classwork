package learn.gomoku.ui;

import learn.gomoku.game.Stone;
import learn.gomoku.game.Gomoku;

import java.util.Scanner;

public class GameBoard {

    Scanner console = new Scanner(System.in);
    int[][] board = new int[Gomoku.WIDTH][Gomoku.WIDTH];
    public void printBoard(Stone move, GamePiece symbol) {

        int counter = 2;
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
//                if (col == move.getColumn() && row == move.getRow()) {
//                    System.out.print(symbol.getSymbol());
//                }
//                else
                if (board[row][col] == '\u0000') {
                    System.out.print(" _ ");
                } else {
                    System.out.printf(" %s ", board[row][col]);
                }
            }
            System.out.println();
            if (row + counter < board.length + 1) {
                System.out.printf("%-2s", ((row + counter) < 10) ? "0" + (row + counter) : (row + counter));
            }
        }

    }

//    public void rePrint(Gomoku mainGame, Stone game) {
//
//        if(game.getRow() > Gomoku.WIDTH || game.getRow() < 0 ||
//                game.getColumn() > Gomoku.WIDTH ||game.getColumn() < 0){
//            return;
//        }
//        if (mainGame.isBlacksTurn() == true ){
//            board[game.getRow()][game.getColumn()] = 'X';
//
//        } else {
//            board[game.getRow()][game.getColumn()] = 'O';
//        }
//
//        printBoard();
//    }
}
