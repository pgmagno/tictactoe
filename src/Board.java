import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private String[][] board = new String[3][3];
    private int numberOfTurns = 0;
    private String[] playerSymbol = new String[2];
    private String playerTurn;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_ITABlue = "\u001B[36;3m";
    public static final String ANSI_ITAPurple = "\u001B[34;3m";

    public Board() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = "   ";
            }
        }
        playerSymbol[0] = " X ";
        playerSymbol[1] = " O ";
    }

    public void drawBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j == 2 && (i == 0 || i == 1 || i == 2)) {
                    System.out.print(board[i][j]);
                } else {
                    System.out.print(board[i][j] + " | ");
                }
            }
            if (i != 2) {
                System.out.println();
                System.out.println("----+-----+----");
            }
        }
        System.out.println();
    }

    public void drawFinalBoard(int num0, int num1, int num2, int num3, int num4, int num5) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (j == 2 && (i == 0 || i == 1 || i == 2)) {
                    if((i == num0 && j == num1) || (i == num2 && j == num3) || (i == num4 && j == num5)) {
                        System.out.print(ANSI_RED + board[i][j] + ANSI_RESET);
                    } else {
                        System.out.print(board[i][j]);
                    }
                } else {
                    if ((i == num0 && j == num1) || (i == num2 && j == num3) || (i == num4 && j == num5)) {
                        System.out.print(ANSI_RED + board[i][j] + ANSI_RESET + " | ");
                    } else {
                        System.out.print(board[i][j] + " | ");
                    }
                }
            }
            if (i != 2) {
                System.out.println();
                System.out.println("----+-----+----");
            }
        }
        System.out.println();
    }



    public boolean gameCheck() {

        boolean gameEnded = false;

        int[][] combMatrix = {
                {0, 0, 0, 1, 0, 2}, //  linha superior
                {1, 0, 1, 1, 1, 2}, //  linha central
                {2, 0, 2, 1, 2, 2}, //  linha inferior
                {0, 0, 1, 0, 2, 0}, //  coluna esquerda
                {0, 1, 1, 1, 2, 1}, //  coluna central
                {0, 2, 1, 2, 2, 2}, //  coluna direita
                {0, 0, 1, 1, 2, 2}, //  diagonal decrescente
                {0, 2, 1, 1, 2, 0}}; // diagonal crescente

        gameCheckLoop:
        for (String symbol : playerSymbol) {

            for (int[] num : combMatrix) {
                if (board[num[0]][num[1]].equals(symbol) &&
                        board[num[2]][num[3]].equals(symbol) &&
                        board[num[4]][num[5]].equals(symbol)) {
                    System.out.println(ANSI_ITABlue + "Winner: Player" + symbol + ANSI_RESET);
                    gameEnded = true;
                    drawFinalBoard(num[0], num[1], num[2], num[3], num[4], num[5]);
                    break gameCheckLoop;
                }
            }
        }
        return gameEnded;
    }

    public void makeMove(String[] move, String playerSymbol) {
        if (board[Integer.parseInt(move[0]) - 1][Integer.parseInt(move[1]) - 1].equals("   ")) {
            board[Integer.parseInt(move[0]) - 1][Integer.parseInt(move[1]) - 1] = playerSymbol;
            switchPlayer();
            numberOfTurns++;
        } else {
            System.out.println(ANSI_RED + "Movimento não permitido" + ANSI_RESET);
        }
    }

    public void randomChooser () {

        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        if (randomNum == 1) {
            playerTurn = playerSymbol[0];
        } else {
            playerTurn = playerSymbol[1];
        }
    }

    public void switchPlayer () {
        if (playerTurn.equals(playerSymbol[0])) {
            playerTurn = playerSymbol[1];
        } else {
            playerTurn = playerSymbol[0];
        }
    }

    public int getNumberOfTurns () {
        return numberOfTurns;
    }

    public String getPlayerTurn() {
        return playerTurn;
    }
}
