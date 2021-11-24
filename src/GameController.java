import java.util.Scanner;

public class GameController {

    public static void main (String[] args){
        System.out.println("Bem-vindo ao Jogo da Velha");
        System.out.println("Digite no terminal uma combinação de \"linha coluna\"");
        System.out.println("Por exemplo: 1 1");
        System.out.println("Esse comando irá colocar o símbolo célula no canto esquerdo superior");
        System.out.println("Linhas e colunas vão de 1 a 3");
        System.out.println("Decidam entre si a quem pertencerá os símbolos \"X\" ou \"O\".");
        System.out.println("O jogo sorteará um dos símbolos para ser o primeiro a jogar.");
        System.out.println("Pressione \"Enter\" quando estiverem prontos!");
        Scanner readySetGo = new Scanner(System.in);
        readySetGo.nextLine();





        Board myBoard = new Board();
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
