import java.util.concurrent.ThreadLocalRandom;

public class Board {

    private String[][] board = new String[3][3];
    private int numberOfTurns = 0;
    private String[] playerSymbol = new String[2];
    private String playerTurn;
    private int gameMode;


    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_ITABlue = "\u001B[36;3m";
    public static final String ANSI_ITAPurple = "\u001B[34;3m";

    // CONSTRUCTOR of the class Board. Cleans the board off null values, replacing it with "   ", assigns symbols to array
    public Board(int gameMode) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = "   ";
            }
        }
        playerSymbol[0] = " X ";
        playerSymbol[1] = " O ";
        this.gameMode = gameMode;
    }
    // This method draws the board taking the values assigned in the matrix board[][], while also adding formatting to
    // give the look of a typical Tic Tac Toe board.
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

    // This method works the same way as drawBoard() with the difference that it receives the matrix coordinates that caused
    // the game to end and formats the symbols within in a different color to show the finishing move.
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

    // This method is responsible for checking if the game has ended in wins or draws, declares the winner or the draw and
    // causes the game to end, then calling the drawFinalBoard with the arguments (matrix coordinates) needed to format
    // the final board.
    // it works by checking the symbols assigned to the board matrix against a combination matrix (combMatrix). It does it
    // by the use of two forEach loops inside each other. Everytime this method is called it checks all possible winning
    // conditions for both symbols (X or O) to figure out if the game has ended or if it has completed the maximum number
    // of turns (9), in which case there was a draw.
    public boolean gameCheck() {

        int[][] combMatrix = {
                {0, 0, 0, 1, 0, 2}, //  linha superior
                {1, 0, 1, 1, 1, 2}, //  linha central
                {2, 0, 2, 1, 2, 2}, //  linha inferior
                {0, 0, 1, 0, 2, 0}, //  coluna esquerda
                {0, 1, 1, 1, 2, 1}, //  coluna central
                {0, 2, 1, 2, 2, 2}, //  coluna direita
                {0, 0, 1, 1, 2, 2}, //  diagonal decrescente
                {0, 2, 1, 1, 2, 0}}; // diagonal crescente

        // checking for winners. This loop has to complete 2 times one for each symbol, 8 times for each winning condition
        // in order to check the next condition (number of maximum turns reached).
        for (String symbol : playerSymbol) {

            for (int[] num : combMatrix) {
                if (board[num[0]][num[1]].equals(symbol) &&
                        board[num[2]][num[3]].equals(symbol) &&
                        board[num[4]][num[5]].equals(symbol)) {
                    System.out.println(ANSI_ITAPurple + ".: GAME OVER :." + ANSI_RESET);
                    System.out.println(ANSI_ITABlue + "Winner: Player" + symbol + "!" + ANSI_RESET);
                    drawFinalBoard(num[0], num[1], num[2], num[3], num[4], num[5]);
                    return true;
                }
            }
        }
        // checking if we have reached the maximum number of turns without a win
        if (numberOfTurns == 9) {
            System.out.println(ANSI_ITAPurple + ".: GAME OVER :." + ANSI_RESET);
            System.out.println(ANSI_ITABlue + "DRAW!" + ANSI_RESET);
            drawBoard();
            return true;
        }
        return false;
    }

    // this method is used to assign the move made by the player into the board matrix
    // before it does, it checks if the move was valid, in which case it will assign the player symbol,
    // call the switchPlayer method and finally update the numberOfTurns.
    // A valid move is one that that fills an empty cell. An empty cell is one that only contains "   ".
    // If it has another symbol in it, it's not valid. In this case, the main game loop will loop again without having
    // switched the player, forcing a valid move to be made.
    public void makeMove(String[] move, String playerSymbol) {
        if (board[Integer.parseInt(move[0]) - 1][Integer.parseInt(move[1]) - 1].equals("   ")) {
            board[Integer.parseInt(move[0]) - 1][Integer.parseInt(move[1]) - 1] = playerSymbol;
            switchPlayer();
            numberOfTurns++;
        } else {
            System.out.println(ANSI_RED + "Movimento não permitido" + ANSI_RESET);
        }
    }

    // This method is responsible for randomly picking the first player to initiate the game.
    public void randomChooser () {

        int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
        if (randomNum == 1) {
            playerTurn = playerSymbol[0];
        } else {
            playerTurn = playerSymbol[1];
        }
    }

    // this method is responsible for switching a playerTurn variable.
    public void switchPlayer () {
        if (playerTurn.equals(playerSymbol[0])) {
            playerTurn = playerSymbol[1];
        } else {
            playerTurn = playerSymbol[0];
        }
    }

    // this get method was created to avoid checking for game ending conditions before is technically possible.
    // lines of code in the game controller class will call this to check if the minimum number of turns was reached (5),
    // in which case it will now be possible for the game to end, and so it is now necessary to check for this event.
    public int getNumberOfTurns () {
        return numberOfTurns;
    }

    // this get method was created to check whose turn it is to play. There will be situations in which the player will make
    // an invalid move for any number of reasons, which will not cause the switchPlayer() method to be called, making
    // it necessary to check every time whose turn is it, if it has changed or not, in order to maintain the game's rules
    public String getPlayerTurn() {
        return playerTurn;
    }

    public void gameMode () {

    }
    // player vs CPU
    // create a list of possible player: humanPlayer, cpuEasy, cpuNormal, cpuHard.
    // when humanPlayer is selected, gameController receives input
    // the makeMove method receives the CPU input instead of the players', no changes needed for makeMove
    // when checking for playerTurn, create a second array with the possible players instead of just "symbols" as players
    // there will always be two players: either human vs one of the cpus or human vs human
    // so the switchPlayer method will switch between the elements inside this array
    // the array is formed by a gameMode method(to be created), switchPlayer will receive this array to work with
    //finally, in gameController, before trying to receive input from the player, check if it's human or cpu.
    //if human, receive input, if cpu receive input from a method cpuMove (to be created)
    // cpuMove receives the array of players to identify which level it is (easy, normal, hard)
    // upon receiving, switch between 3 possible scenarios: random possible move - easy, calculated blocking stratey - normal
    // calculated winning strategy - hard

    // create a new interface to be easier to play. Next to the board, create a mock board with numbers to show
    // which single number represents the cell to be selected. after each move, these numbers will be formatted with
    // another color, to show that it's no longer possible to make such move
    //   1   2   3
    //   4   5   6
    //   7   8   9
    // create a method to figure out which number represents which combination of row and column,
    // possible need to refactor all other methods.

}

