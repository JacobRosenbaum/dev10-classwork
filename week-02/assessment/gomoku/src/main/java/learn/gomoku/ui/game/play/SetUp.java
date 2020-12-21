package learn.gomoku.ui.game.play;

import learn.gomoku.game.Gomoku;
import learn.gomoku.players.Player;
import learn.gomoku.ui.game.item.GamePiece;
import learn.gomoku.ui.game.item.BlackGamePiece;
import learn.gomoku.ui.game.item.WhiteGamePiece;
import java.util.Scanner;

public class SetUp {
    Scanner console = new Scanner(System.in);
    SetPlayers setPlayer = new SetPlayers();

    public void startGame() {

        System.out.print("Welcome to Gomoku\n================\n\n");
        Player player1 = setPlayer.setPlayer1(console);
        System.out.println("\nPlayer 1 is " + player1.getName());
        Player player2 = setPlayer.setPlayer2(console);
        System.out.println("\nPlayer 2 is " + player2.getName());

        Gomoku currentPlayer = new Gomoku(player1, player2);
        GamePiece blackPiece = new BlackGamePiece();
        GamePiece whitePiece = new WhiteGamePiece();
        PlayGame game = new PlayGame();

        System.out.println("\nRandomizing...\n");
        System.out.println(currentPlayer.getCurrent().getName() + " goes first.");
        game.playGame(currentPlayer, player1, player2, blackPiece, whitePiece);

    }

}
