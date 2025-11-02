package LLDSnakeNLadderGame;

import LLDSnakeNLadderGame.entity.Board;
import LLDSnakeNLadderGame.entity.Player;
import LLDSnakeNLadderGame.enums.Difficulty;
import LLDSnakeNLadderGame.factory.SnakeAndLadderGameFactory;
import LLDSnakeNLadderGame.observer.IObserver;
import LLDSnakeNLadderGame.service.SnakeAndLadderGame;
import LLDSnakeNLadderGame.strategy.*;
import LLDSnakeNLadderGame.observer.*;

import java.util.Scanner;

public class SnakeAndLadderDemo {
    public  static void main(String[] args) {
        System.out.println("=== SNAKE AND LADDER GAME ===");
        SnakeAndLadderGame game = null;
        Board board = null;

        System.out.println("Choose game setup:");
        System.out.println("1. Standard Game (10x10 board with traditional positions)");
        System.out.println("2. Random Game with Difficulty");
        System.out.println("3. Custom Game");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        if(choice == 1) {
            // Standard game
            game = SnakeAndLadderGameFactory.createStandardGame();
            board = new Board(10);
        }
        else if(choice == 2) {
            // Random game with difficulty
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();

            System.out.println("Choose difficulty:");
            System.out.println("1. Easy (more ladders)");
            System.out.println("2. Medium (balanced)");
            System.out.println("3. Hard (more snakes)");

            int diffChoice = scanner.nextInt();

            Difficulty diff;

            switch(diffChoice) {
                case 1: diff = Difficulty.EASY; break;
                case 3: diff = Difficulty.HARD; break;
                default: diff = Difficulty.MEDIUM;
            }

            game = SnakeAndLadderGameFactory.createRandomGame(boardSize, diff);
            board = new Board(boardSize);
        }
        else if(choice == 3) {
            System.out.print("Enter board size (e.g., 10 for 10x10 board): ");
            int boardSize = scanner.nextInt();

            System.out.println("Choose custom setup type:");
            System.out.println("1. Specify counts only (random placement)");
            System.out.println("2. Specify exact positions");

            int customChoice = scanner.nextInt();

            if(customChoice == 1) {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();

                BoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, true);
                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);
            }
            else {
                System.out.print("Enter number of snakes: ");
                int numSnakes = scanner.nextInt();
                System.out.print("Enter number of ladders: ");
                int numLadders = scanner.nextInt();

                CustomCountBoardSetupStrategy strategy = new CustomCountBoardSetupStrategy(numSnakes, numLadders, false);

                // Get snake positions
                for(int i = 0; i < numSnakes; i++) {
                    System.out.print("Enter snake " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addSnakePosition(start, end);
                }

                // Get ladder positions
                for(int i = 0; i < numLadders; i++) {
                    System.out.print("Enter ladder " + (i+1) + " start and end positions: ");
                    int start = scanner.nextInt();
                    int end = scanner.nextInt();
                    strategy.addLadderPosition(start, end);
                }

                game = SnakeAndLadderGameFactory.createCustomGame(boardSize, strategy);
            }

            board = new Board(boardSize);
        }

        if(game == null) {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }

        // Add observer
        IObserver notifier = new ConsoleNotifierObserver();
        game.addObserver(notifier);

        // Create players
        System.out.print("Enter number of players: ");
        int numPlayers = scanner.nextInt();
        scanner.nextLine(); // consume newline

        for(int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for player " + (i+1) + ": ");
            String name = scanner.nextLine();
            Player player = new Player(i+1, name);
            game.addPlayer(player);
        }

        game.play();

        scanner.close();
    }
}
