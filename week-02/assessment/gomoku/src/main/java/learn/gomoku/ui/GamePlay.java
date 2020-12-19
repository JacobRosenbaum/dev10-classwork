package learn.gomoku.ui;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.Scanner;

public class GamePlay {
    Scanner console = new Scanner(System.in);

    public void runGame() {

        System.out.print("Welcome to Gomoku\n================\n\n");
        Player player1 = setPlayer1(console);
        Player player2 = setPlayer2(console);
        Gomoku currentPlayer = new Gomoku(player1, player2);

        System.out.println("\nRandomizing...\n");
        System.out.println(currentPlayer.getCurrent().getName() + " goes first.\n");
        playGame(currentPlayer, player1, player2);

    }

    public void printWorld() {
        int rowWidth = 15;
        int colWidth = 15;
        int[][] board = new int[rowWidth][colWidth];
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
            System.out.print("\nPlayer 2, printith thou name: ");
            Player player2 = new HumanPlayer(console.nextLine());
            return player2;
        } else if (player.equals("2")) {
            Player player2 = new RandomPlayer();
            return player2;
        }
        return null;
    }

    public Stone getBlackStone(Stone blackStoneMove, Gomoku currentPlayer) {
        Stone blackStone;
        if (blackStoneMove == (null)) {
            System.out.println(currentPlayer.getCurrent().getName() + "'s turn");
            System.out.print("Enter a row: ");
            int row = Integer.parseInt(console.nextLine());
            System.out.print("Enter a column: ");
            int column = Integer.parseInt(console.nextLine());
            return blackStone = new Stone(row, column, true);
        } else {
            return blackStone = blackStoneMove;
        }
    }

    public Stone getWhiteStone(Stone whiteStoneMove, Gomoku currentPlayer) {
        Stone whiteStone;
        if (whiteStoneMove == (null)) {
            System.out.println(currentPlayer.getCurrent().getName() + "'s turn");
            System.out.print("Enter a row: ");
            int row = Integer.parseInt(console.nextLine());
            System.out.print("Enter a column: ");
            int column = Integer.parseInt(console.nextLine());
            return whiteStone = new Stone(row, column, true);
        } else {
            return whiteStone = whiteStoneMove;
        }
    }

    public void playGame(Gomoku currentPlayer, Player player1, Player player2){
        Stone blackStoneMove;
        if (currentPlayer == player1){
            blackStoneMove = player1.generateMove(currentPlayer.getStones());
        }
        else{
            blackStoneMove = player2.generateMove(currentPlayer.getStones());
        }
        getBlackStone(blackStoneMove, currentPlayer);

        currentPlayer.swap();
        printWorld();

        Stone whiteStoneMove;
        if (currentPlayer == player2){
            whiteStoneMove = player2.generateMove(currentPlayer.getStones());
        }
        else{
            whiteStoneMove = player1.generateMove(currentPlayer.getStones());
        }
        getWhiteStone(whiteStoneMove, currentPlayer);

        currentPlayer.swap();
        printWorld();
    }

}


//        player1.generateMove(currentPlayer.getStones());
//        player2.generateMove(currentPlayer.getStones());

//        do {
//            if (player1.generateMove(currentPlayer.getStones()) == null) {
//                System.out.println(firstPlayer + "'s turn.\n");
//                System.out.print("Enter a row: ");
//                int row = Integer.parseInt(console.nextLine());
//                System.out.print("\nEnter a column: ");
//                int column = Integer.parseInt(console.nextLine());
//                Stone blackPlayer = new Stone(row, column, true);
//                currentPlayer.swap();
//            } else if (player1.generateMove(currentPlayer.getStones()) != null) {
//                System.out.println(firstPlayer + "'s turn.\n");
//                player1.generateMove(currentPlayer.getStones());
//                currentPlayer.swap();
//            }
//        } while(currentPlayer.isOver() == false);
//           List<Stones> stones = currentPlayer.getStones();

//            if ((player1 == new RandomPlayer()) && (player1 == currentPlayer)){
//                player1.generateMove();
//            }
//
//            else if ((player1 == new HumanPlayer()) && (player1 == currentPlayer)){
//                System.out.println("Enter a row: ");
//                int row = console.nextInt();
//                System.out.println("Enter a column: ");
//                int column = console.nextInt();
//
//            }