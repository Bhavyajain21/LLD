package LLDTicTacToe;

import LLDTicTacToe.entity.Player;
import LLDTicTacToe.entity.Symbol;
import LLDTicTacToe.enums.GameType;
import LLDTicTacToe.factory.TicTacToeGameFactory;
import LLDTicTacToe.observers.ConsoleNotifierObserver;
import LLDTicTacToe.observers.IObserver;

import java.util.Scanner;

public class TicTacToeMain {
    public static void main(String[] args) {
        System.out.println("=== TIC TAC TOE GAME ===");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter board size (e.g., 3 for 3x3): ");
        int boardSize = scanner.nextInt();

        Game game = TicTacToeGameFactory.createGame(GameType.STANDARD, boardSize);

        // Add observer
        IObserver notifier = new ConsoleNotifierObserver();
        game.addObserver(notifier);

        // Create players with custom symbols
        Player player1 = new Player(1, "Bhavya", new Symbol('X'));
        Player player2 = new Player(2, "Kartik", new Symbol('O'));

        game.addPlayer(player1);
        game.addPlayer(player2);

        // Play the game
        game.play();

        scanner.close();
    }
}
