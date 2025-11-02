package LLDSnakeNLadderGame.factory;

import LLDSnakeNLadderGame.entity.*;
import LLDSnakeNLadderGame.enums.Difficulty;
import LLDSnakeNLadderGame.service.SnakeAndLadderGame;
import LLDSnakeNLadderGame.strategy.*;

public class SnakeAndLadderGameFactory {
    public static SnakeAndLadderGame createStandardGame() {
        Board board = new Board(10);  // Standard 10x10 board
        BoardSetupStrategy strategy = new StandardBoardSetupStrategy();
        board.setupBoard(strategy);

        Dice dice = new Dice(6);  // Standard 6-faced dice

        return new SnakeAndLadderGame(board, dice);
    }

    public static SnakeAndLadderGame createRandomGame(int boardSize, Difficulty difficulty) {
        Board board = new Board(boardSize);
        BoardSetupStrategy strategy = new RandomBoardSetupStrategy(difficulty);
        board.setupBoard(strategy);

        Dice dice = new Dice(6);

        return new SnakeAndLadderGame(board, dice);
    }

    public static SnakeAndLadderGame createCustomGame(int boardSize, BoardSetupStrategy strategy) {
        Board board = new Board(boardSize);
        board.setupBoard(strategy);

        Dice dice = new Dice(6);

        return new SnakeAndLadderGame(board, dice);
    }
}