package LLDSnakeNLadderGame.strategy.Rules;

import LLDSnakeNLadderGame.entity.Board;

public interface SnakeAndLadderRule {
    boolean isValidMove(int currentPosition, int diceValue, int boardSize);
    int calculateNewPosition(int currentPosition, int diceValue, Board board);
    boolean checkWinCondition(int position, int boardSize);
}
