package learn.gomoku.ui.game.play;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;
import learn.gomoku.ui.game.item.GamePiece;

import java.util.ArrayList;

public class PlayGame {

    GetStones getStone = new GetStones();
    GameBoard board = new GameBoard();
    EndGame endGame = new EndGame();

    public void playGame(Gomoku currentPlayer, Player player1, Player player2, GamePiece blackPiece,
                         GamePiece whitePiece) {
//        ArrayList<Stone> whiteList = new ArrayList<>();
//        ArrayList<Stone> blackList = new ArrayList<>();
        Result whiteMove = null;
        Result blackMove = null;

        do {
            Stone blackStoneMove;
            Stone blackStone;
            if (currentPlayer.isBlacksTurn() == true) {
                if (currentPlayer.getCurrent().getName() == player1.getName()) {
                    blackStoneMove = player1.generateMove(currentPlayer.getStones());
                } else {
                    blackStoneMove = player2.generateMove(currentPlayer.getStones());
                }
                blackStone = getStone.getBlackStone(blackStoneMove, currentPlayer);
//            blackList.add(blackStone);
                blackMove = currentPlayer.place(blackStone);
                if (blackMove.getMessage() == null) {
                    if (blackStone == blackStoneMove) {
                        board.printBoard(blackStoneMove, whitePiece, player1, player2, currentPlayer);
                    } else {
                        board.printBoard(blackStone, whitePiece, player1, player2, currentPlayer);
                    }
                } else {
                    if (blackMove.getMessage().equals(currentPlayer.getCurrent().getName() + " wins.")) {
                        System.out.println(blackMove.getMessage() + "\n");
                        if (blackStone == blackStoneMove) {
                            board.printBoard(blackStoneMove, whitePiece, player1, player2, currentPlayer);
                        } else {
                            board.printBoard(blackStone, whitePiece, player1, player2, currentPlayer);
                        }
                        break;
                    } else if (blackMove.getMessage() != null) {
                        System.out.println(blackMove.getMessage());

                    }
                }
            }

            Stone whiteStone;
            Stone whiteStoneMove;
            if (currentPlayer.isBlacksTurn() == false) {
                if (currentPlayer.getCurrent().getName() == player1.getName()) {
                    whiteStoneMove = player1.generateMove(currentPlayer.getStones());
                } else {
                    whiteStoneMove = player2.generateMove(currentPlayer.getStones());
                }
                whiteStone = getStone.getWhiteStone(whiteStoneMove, currentPlayer);
//            whiteList.add(whiteStone);
                whiteMove = currentPlayer.place(whiteStone);
                if (whiteMove.getMessage() == null) {
                    if (whiteStone == whiteStoneMove) {
                        board.printBoard(whiteStoneMove, whitePiece, player1, player2, currentPlayer);
                    } else {
                        board.printBoard(whiteStone, whitePiece, player1, player2, currentPlayer);
                    }
                } else {
                    if (whiteMove.getMessage().equals(currentPlayer.getCurrent().getName() + " wins.")) {
                        System.out.println(whiteMove.getMessage() + "\n");
                        if (whiteStone == whiteStoneMove) {
                            board.printBoard(whiteStoneMove, whitePiece, player1, player2, currentPlayer);
                        } else {
                            board.printBoard(whiteStone, whitePiece, player1, player2, currentPlayer);
                        }
                        break;
                    } else if (whiteMove.getMessage() != null) {
                        System.out.println(whiteMove.getMessage());

                    }

                }
            }
        } while ((whiteMove.getMessage() != (currentPlayer.getCurrent().getName() + " wins.")) || (blackMove.getMessage() != (currentPlayer.getCurrent().getName() + " wins.")));

        endGame.restartGame();
    }
}
