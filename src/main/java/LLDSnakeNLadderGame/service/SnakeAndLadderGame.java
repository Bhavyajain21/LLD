package LLDSnakeNLadderGame.service;

import LLDSnakeNLadderGame.entity.Board;
import LLDSnakeNLadderGame.entity.BoardEntity;
import LLDSnakeNLadderGame.entity.Dice;
import LLDSnakeNLadderGame.entity.Player;
import LLDSnakeNLadderGame.observer.IObserver;
import LLDSnakeNLadderGame.strategy.Rules.SnakeAndLadderRule;
import LLDSnakeNLadderGame.strategy.Rules.StandardSnakeAndLadderRule;

import java.util.*;

public class SnakeAndLadderGame {
    private Board board;
    private Dice dice;
    private Deque<Player> players;
    private SnakeAndLadderRule snakeAndLadderRule;
    private List<IObserver> observers;
    private boolean gameOver;

    public SnakeAndLadderGame(Board board, Dice dice) {
        this.board = board;
        this.dice = dice;
        this.gameOver = false;
        this.players = new ArrayDeque<>();
        snakeAndLadderRule = new StandardSnakeAndLadderRule();
        observers = new ArrayList<>();
        gameOver = false;
    }

    public void addPlayer(Player player) {
        players.addLast(player);
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void notify(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public void displayPlayerPositions() {
        System.out.println("\n=== Current Positions ===");
        for(Player player : players) {
            System.out.println(player.getName() + ": " + player.getPosition());
        }
        System.out.println("=======================");
    }

    public void play() {
        if(players.size()<2) {
            System.out.println("Need at least 2 players!");
            return;
        }

        notify("Game Started");

        board.display();

        Scanner scanner = new Scanner(System.in);

        while(!gameOver) {
            Player currentPlayer = players.peekFirst();
            System.out.println("\n" + currentPlayer.getName() + "'s turn. Press Enter to roll dice...");
            scanner.nextLine();

            int diceValue = dice.roll();
            System.out.println("Rolled: " + diceValue);

            int currentPos = currentPlayer.getPosition();
            if(snakeAndLadderRule.isValidMove(currentPos, diceValue, board.getBoardSize())) {
                int intermediatePos = currentPos + diceValue;
                int newPos = snakeAndLadderRule.calculateNewPosition(currentPos, diceValue, board);

                currentPlayer.setPosition(newPos);

                // Check if player encountered snake or ladder
                BoardEntity entity = board.getEntity(intermediatePos);
                if(entity != null) {
                    boolean isSnake = entity.name().equals("SNAKE");
                    if(isSnake) {
                        System.out.println("Oh no! Snake at " + intermediatePos + "! Going down to " + newPos);
                        notify(currentPlayer.getName() + " encountered snake at " + intermediatePos + " now going down to " + newPos);
                    }
                    else {
                        System.out.println("Great! Ladder at " + intermediatePos + "! Going up to " + newPos);
                        notify(currentPlayer.getName() + " encountered ladder at " + intermediatePos + " now going up to " + newPos);
                    }
                }
                notify(currentPlayer.getName() + " played. New Position : " + newPos);
                displayPlayerPositions();

                if(snakeAndLadderRule.checkWinCondition(newPos, board.getBoardSize())) {
                    System.out.println("\n" + currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify("Game Ended. Winner is : " + currentPlayer.getName());
                    gameOver = true;
                }
                else{
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Need exact roll to reach " + board.getBoardSize() + "!");
                // Move player to back of queue
                players.removeFirst();
                players.addLast(currentPlayer);
            }
        }
        scanner.close();
    }
}
