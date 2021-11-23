import java.util.Scanner;

public class GameController {



    public static void main (String[] args){
        System.out.println("Bem-vindo ao Jogo da Velha");
        System.out.println("Digite no terminal uma combinação de \"linha coluna\"");
        System.out.println("Por exemplo: 1 1");
        System.out.println("Esse comando irá colocar o símbolo célula no canto esquerdo superior");
        System.out.println("Linhas e colunas vão de 1 a 3");

        Board myBoard = new Board();
        myBoard.drawBoard();
        Scanner playerInput = new Scanner(System.in);

        while(true) {
            String symbol = myBoard.moveSelector();
            System.out.print("Digite o movimento Jogador (" + symbol + ") : ");
            String[] playerCommand = new String[2];
            try {
                playerCommand = playerInput.nextLine().split(" ");
                myBoard.makeMove(playerCommand, symbol);
            } catch (ArrayIndexOutOfBoundsException exception) {
                System.out.println("Digite o número certo");
            }

            myBoard.drawBoard();

            if (myBoard.gameCheck()) {
                System.out.println("Bye bye");
                break;
            }


        }

    }
}
