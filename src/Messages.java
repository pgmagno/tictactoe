public class Messages {

    public void welcome() {
        System.out.println(".: TIC TAC TOE :.");
        System.out.println("Welcome to the Java Tic tac toe!");
        System.out.println("The rules are simple. Type a number that matches the cell you want to place your token");
        System.out.println("The cells are numbered from 1 to 9!");
        System.out.println("Select the game mode:");
        System.out.println("Type 1 for Human vs CPU");
        System.out.println("Type 2 for Human vs Human");
        System.out.println("Type 3 to QUIT");
    }
    public void humanVsHumanMode() {
        System.out.println("Decide among you who will take which symbol: \"X\" or \"O\".");
        System.out.println("The game will select randomly one of the symbols to make the first move.");
        System.out.println("Press \"Enter\" when you are ready!");
    }
    public void humanVsCPUMode() {
        System.out.println("Human vs CPU Selected");
    }
    public void exit() {
        System.out.println("Shutting down application.");
    }


}
