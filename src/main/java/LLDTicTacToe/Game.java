package LLDTicTacToe;

import LLDTicTacToe.entity.*;
import LLDTicTacToe.observers.IObserver;
import LLDTicTacToe.strategy.Rules;
import LLDTicTacToe.strategy.StandardTicTacToeRule;

import java.util.*;

public class Game {
    private Board board;
    private Deque<Player> players;
    private Rules rule;
    private List<IObserver> observers;
    private boolean gameOver;
    public Game(int boardSize) {
        board = new Board(boardSize);
        players = new ArrayDeque<>();
        rule = new StandardTicTacToeRule();
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
        for(IObserver observer: observers) {
            observer.notify(message);
        }
    }

    public void play(){
        if(players.size() < 2) {
            System.out.println("Need at least 2 players!");
            return;
        }

        notify("Tic Tac Toe Game Started!");

        Scanner sc = new Scanner(System.in);

        while(!gameOver) {
            board.display();

            Player currentPlayer = players.peekFirst();
            System.out.print(currentPlayer.getName() + " (" + currentPlayer.getSymbol().getMark() + ") - Enter row and column: ");

            int row = sc.nextInt();
            int col = sc.nextInt();

            // check if move is valid
            if(rule.isValidMove(board,row,col)){
                board.placeMark(row,col,currentPlayer.getSymbol());
                notify(currentPlayer.getName() + " played (" + row + "," + col + ")");
                if(rule.checkWinCondition(board, currentPlayer.getSymbol())) {
                    board.display();
                    System.out.println(currentPlayer.getName() + " wins!");
                    currentPlayer.incrementScore();

                    notify(currentPlayer.getName() + " wins!");

                    gameOver = true;
                }
                else if(rule.checkDrawCondition(board)) {
                    board.display();

                    System.out.println("It's a draw!");
                    notify("Game is Draw!");

                    gameOver = true;
                }
                else {
                    // Move player to back of queue
                    players.removeFirst();
                    players.addLast(currentPlayer);
                }
            }
            else {
                System.out.println("Invalid move! Try again.");
            }
        }

    }
}
