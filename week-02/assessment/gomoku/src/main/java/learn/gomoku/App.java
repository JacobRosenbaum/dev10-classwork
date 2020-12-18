package learn.gomoku;

import learn.gomoku.game.Gomoku;
import learn.gomoku.ui.SetUp;

public class App {

    public static void main(String[] args) {
        SetUp game = new SetUp();
        game.startGame();
    }
}
