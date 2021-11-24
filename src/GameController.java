import java.util.InputMismatchException;
import java.util.Scanner;

public class GameController {

    public static void main (String[] args){

        Messages msg = new Messages();
        msg.welcome();

        //receiving player choice of game mode
        Scanner gamePrep = new Scanner(System.in);

        int gameModeChoice;
        while (true) {
            try {
                gameModeChoice = gamePrep.nextInt();
                if (gameModeChoice > 0 && gameModeChoice <= 3) {
                    break;
                } else {
                    System.out.println("Pick a valid game mode (1 or 2)");
                }
            } catch (InputMismatchException |
                    ArrayIndexOutOfBoundsException |
                    NumberFormatException exception) {
                System.out.println("Pick a valid game mode (1 or 2)");
                gamePrep.nextLine();
            }
        }

        switch (gameModeChoice) {

            case 1:
                msg.humanVsCPUMode();
                break;
            case 2:
                msg.humanVsHumanMode();
                gamePrep.nextLine();
                break;
            case 3:
                msg.exit();
                quit;
            default:
                System.out.println();
        }

        Board myBoard = new Board(gameModeChoice);
        myBoard.drawBoard();
        Scanner playerInput = new Scanner(System.in);

        String symbol;
        myBoard.randomChooser();
        String[] playerCommand;

        while(true) {
            symbol = myBoard.getPlayerTurn();
            System.out.print("Digite o movimento, Jogador (" + symbol + ") : ");

            try {
                playerCommand = playerInput.nextLine().split(" ");
                myBoard.makeMove(playerCommand, symbol);
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException exception) {
                System.out.println("Digite a combinação correta de linha e coluna. Ex: 1 1");
            }

            myBoard.drawBoard();

            if (myBoard.getNumberOfTurns() >= 5) {
                if (myBoard.gameCheck()) {
                    System.out.println("Thank you for playing!");
                    break;
                }
            }
        }

    }
}
