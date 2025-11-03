package LLDTicTacToe.strategy;

import LLDTicTacToe.entity.*;

public abstract class Rules {
    public abstract boolean isValidMove(Board board, int row, int col);
    public abstract boolean checkWinCondition(Board board, Symbol symbol);
    public abstract boolean checkDrawCondition(Board board);
}
