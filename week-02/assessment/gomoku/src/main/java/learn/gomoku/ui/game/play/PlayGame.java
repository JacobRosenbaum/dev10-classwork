package learn.gomoku.ui.game.play;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.Player;
import learn.gomoku.ui.game.item.GamePiece;
import java.util.ArrayList;

public class PlayGame {

    GetStones getStone = new GetStones();
    GameBoard board = new GameBoard();

    public void playGame(Gomoku currentPlayer, Player player1, Player player2, GamePiece blackPiece,
                         GamePiece whitePiece) {
        ArrayList<Stone> whiteList = new ArrayList<>();
        ArrayList<Stone> blackList = new ArrayList<>();

        do {
            Stone blackStoneMove;
            Stone blackStone;
            if (currentPlayer.getCurrent().getName() == player1.getName()) {
                blackStoneMove = player1.generateMove(currentPlayer.getStones());
            } else {
                blackStoneMove = player2.generateMove(currentPlayer.getStones());
            }
            blackStone = getStone.getBlackStone(blackStoneMove, currentPlayer);
            blackList.add(blackStone);
            currentPlayer.place(blackStone);
            if (blackStone == blackStoneMove) {
                board.printBoard(blackStoneMove, blackPiece, player1, player2, currentPlayer);
            } else {
                board.printBoard(blackStone, blackPiece, player1, player2, currentPlayer);
            }
//            currentPlayer.swap();
            Stone whiteStone;
            Stone whiteStoneMove;
            if (currentPlayer.getCurrent().getName() == player1.getName()) {
                whiteStoneMove = player1.generateMove(currentPlayer.getStones());
            } else {
                whiteStoneMove = player2.generateMove(currentPlayer.getStones());
            }
            whiteStone = getStone.getWhiteStone(whiteStoneMove, currentPlayer);
            whiteList.add(whiteStone);
            currentPlayer.place(whiteStone);
            if (whiteStone == whiteStoneMove) {
                board.printBoard(whiteStoneMove, whitePiece, player1, player2, currentPlayer);
            } else {
                board.printBoard(whiteStone, whitePiece, player1, player2, currentPlayer);
            }
        } while (currentPlayer.isOver() == false);
    }
}
