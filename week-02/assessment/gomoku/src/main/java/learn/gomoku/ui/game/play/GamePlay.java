// OLD LOGIC - ALL IN ONE FILE FOR SAFE KEEPING IN CASE OF CRASH

//package learn.gomoku.ui.game.play;
//
//import learn.gomoku.game.Gomoku;
//import learn.gomoku.game.Stone;
//import learn.gomoku.players.HumanPlayer;
//import learn.gomoku.players.Player;
//import learn.gomoku.players.RandomPlayer;
//import learn.gomoku.ui.game.item.GamePiece;
//import learn.gomoku.ui.game.item.WhiteGamePiece
//import learn.gomoku.ui.game.item.BlackGamePiece;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//public class GamePlay {
//    Scanner console = new Scanner(System.in);
//    Stone blackStone;
//    Stone whiteStone;
//    ArrayList<Stone> whiteList = new ArrayList<>();
//    ArrayList<Stone> blackList = new ArrayList<>();
//
//    public void runGame() {
//
//        System.out.print("Welcome to Gomoku\n================\n\n");
//        Player player1 = setPlayer1(console);
//        System.out.println("\nPlayer 1 is " + player1.getName());
//        Player player2 = setPlayer2(console);
//        System.out.println("\nPlayer 2 is " + player2.getName());
//        Gomoku currentPlayer = new Gomoku(player1, player2);
//        GamePiece blackPiece = new BlackGamePiece();
//        GamePiece whitePiece = new WhiteGamePiece();
//
//
//        System.out.println("\nRandomizing...\n");
//        System.out.println(currentPlayer.getCurrent().getName() + " goes first.");
//        Stone blackStone = null;
//        Stone whiteStone = null;
//        playGame(currentPlayer, player1, player2, blackPiece, whitePiece);
//
//    }

//    public void printBoard(Stone stone, GamePiece symbol, Player player1, Player player2, Gomoku currentPlayer) {
//        int rowWidth = Gomoku.WIDTH;
//        int colWidth = Gomoku.WIDTH;
//        int[][] board = new int[rowWidth][colWidth];
//        int counter = 2;
////        int stoneCounter = currentPlayer.getStones().size() - 1;
//        ArrayList<Stone> stoneCounter = new ArrayList<>();
////        Stone test;
//
//
//        for (int i = 0; i <= board.length; i++) {
//            if (i == 0) {
//                System.out.print("  ");
//            } else {
//                System.out.printf("%s ", (i < 10) ? "0" + i : i);
//            }
//        }
//        System.out.println();
//        System.out.print("01");
////        Stone previousStone = currentPlayer.getStones().get().getColumn();
//        for (int row = 0; row < board.length; row++) {
//            for (int col = 0; col < board.length; col++) {
////                if ((col == stone.getColumn()) && (row == stone.getRow())) {
////                    System.out.print(" " + symbol.getSymbol() + " ");
////                }
//                for (int stones = 0; stones < currentPlayer.getStones().size(); stones++) {
//                    stoneCounter.add(currentPlayer.getStones().get(stones));
////                    System.out.println("row: " + (stoneCounter.get(stones).getRow() + 1) + " " + "column: " + (stoneCounter.get(stones).getColumn() + 1));
//
//                    if (row == (stoneCounter.get(stones).getRow()) &&
//                            col == (stoneCounter.get(stones).getColumn()) &&
//                            (stoneCounter.get(stones).isBlack() == true) &&
//                            (board[row][col] == '\u0000')
//                    ) {
//                        System.out.print(" " + "X" + " ");
//                    } else if (row == (stoneCounter.get(stones).getRow()) &&
//                            col == (stoneCounter.get(stones).getColumn()) &&
//                            (stoneCounter.get(stones).isBlack() == false) &&
//                            (board[row][col] == '\u0000')
//                    ) {
//                        System.out.print(" " + "O" + " ");
//                    }
//                }
////                else if ((row == currentPlayer.getStones().get(row).getRow()) && (col == currentPlayer.getStones().get(col).getColumn())){
////                    System.out.print(" " + symbol.getSymbol() + " ");
////                }
////                else if ((col == stoneCounter.get(col).getColumn()) && (row == stoneCounter.get(row).getRow()) && (stoneCounter.get(row).isBlack() == false)){
////                    System.out.println(" O ");
////                }
//                if (board[row][col] == '\u0000' && col < colWidth) {
//                    System.out.print(" _ ");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//            if (row + counter < board.length + 1) {
//                System.out.printf("%-2s", ((row + counter) < 10) ? "0" + (row + counter) : (row + counter));
//            }
//        }
//
//    }
//
//    public Player setPlayer1(Scanner console) {
//        System.out.println("Player 1 is: \n");
//        System.out.print("1. Human\n2. Random Player\nSelect [1-2]: ");
//        String player = console.nextLine();
//        if (player.equals("1")) {
//            System.out.print("\nPlayer 1, printith thou name: ");
//            Player player1 = new HumanPlayer(console.nextLine());
//            return player1;
//        } else if (player.equals("2")) {
//            Player player1 = new RandomPlayer();
//            return player1;
//        }
//        return null;
//    }
//
//    public Player setPlayer2(Scanner console) {
//
//        System.out.println("\nPlayer 2 is: \n");
//        System.out.print("1. Human\n2. Random Player\nSelect [1-2]: ");
//        String player = console.nextLine();
//        if (player.equals("1")) {
//            System.out.print("\nPlayer 2, printith thou name: ");
//            Player player2 = new HumanPlayer(console.nextLine());
//            return player2;
//        } else if (player.equals("2")) {
//            Player player2 = new RandomPlayer();
//            return player2;
//        }
//        return null;
//    }
//
//    public Stone getBlackStone(Stone blackStoneMove, Gomoku currentPlayer) {
//
//        if (currentPlayer.getCurrent().generateMove(currentPlayer.getStones()) == null) {
//            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn");
//            System.out.print("Enter a row: ");
//            int row = Integer.parseInt(console.nextLine());
//            System.out.print("Enter a column: ");
//            int column = Integer.parseInt(console.nextLine());
//            System.out.println();
//            return blackStone = new Stone(row - 1, column - 1, true);
//        } else {
//            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn\n");
//            return blackStone = blackStoneMove;
//        }
//    }
//
//    public Stone getWhiteStone(Stone whiteStoneMove, Gomoku currentPlayer) {
//
//        if (currentPlayer.getCurrent().generateMove(currentPlayer.getStones()) == null) {
//            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn");
//            System.out.print("Enter a row: ");
//            int row = Integer.parseInt(console.nextLine());
//            System.out.print("Enter a column: ");
//            int column = Integer.parseInt(console.nextLine());
//            System.out.println();
//
//            return whiteStone = new Stone(row - 1, column - 1, false);
//        } else {
//            System.out.println("\n" + currentPlayer.getCurrent().getName() + "'s turn\n");
//            return whiteStone = whiteStoneMove;
//        }
//    }
//
//
//    public void playGame(Gomoku currentPlayer, Player player1, Player player2, learn.gomoku.ui.GamePiece blackPiece,
//                         learn.gomoku.ui.GamePiece whitePiece) {
//
//        do {
//            Stone blackStoneMove;
//            Stone blackStone;
//            if (currentPlayer.getCurrent().getName() == player1.getName()) {
//                blackStoneMove = player1.generateMove(currentPlayer.getStones());
//            } else {
//                blackStoneMove = player2.generateMove(currentPlayer.getStones());
//            }
//            blackStone = getBlackStone(blackStoneMove, currentPlayer);
//            blackList.add(blackStone);
//            currentPlayer.place(blackStone);
//            if (blackStone == blackStoneMove) {
//                printBoard(blackStoneMove, blackPiece, player1, player2, currentPlayer);
//            } else {
//                printBoard(blackStone, blackPiece, player1, player2, currentPlayer);
//            }
////            currentPlayer.swap();
//
//            Stone whiteStone;
//            Stone whiteStoneMove;
//            if (currentPlayer.getCurrent().getName() == player1.getName()) {
//                whiteStoneMove = player1.generateMove(currentPlayer.getStones());
//            } else {
//                whiteStoneMove = player2.generateMove(currentPlayer.getStones());
//            }
//            whiteStone = getWhiteStone(whiteStoneMove, currentPlayer);
//            whiteList.add(whiteStone);
//            currentPlayer.place(whiteStone);
//            if (whiteStone == whiteStoneMove) {
//                printBoard(whiteStoneMove, whitePiece, player1, player2, currentPlayer);
//            } else {
//                printBoard(whiteStone, whitePiece, player1, player2, currentPlayer);
//            }
//
//        } while (currentPlayer.isOver() == false);
//
//    }

//}
