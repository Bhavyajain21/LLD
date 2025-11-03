package LLDTicTacToe.strategy;

import LLDTicTacToe.entity.Board;
import LLDTicTacToe.entity.Symbol;

public class StandardTicTacToeRule extends Rules{
    @Override
    public boolean isValidMove(Board board, int row, int col) {
        return board.isCellEmpty(row, col);
    }

    @Override
    public boolean checkWinCondition(Board board, Symbol symbol) {
        int size = board.getSize();

        // Check rows
        for(int i = 0; i < size; i++) {
            boolean win = true;
            for(int j = 0; j < size; j++) {
                if(board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }

        // Check columns
        for(int j = 0; j < size; j++) {
            boolean win = true;
            for(int i = 0; i < size; i++) {
                if(board.getCell(i, j) != symbol) {
                    win = false;
                    break;
                }
            }
            if(win) return true;
        }

        // Check main diagonal
        boolean win = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(i, i) != symbol) {
                win = false;
                break;
            }
        }
        if(win) return true;

        // Check anti-diagonal
        win = true;
        for(int i = 0; i < size; i++) {
            if(board.getCell(i, size-1-i) != symbol) {
                win = false;
                break;
            }
        }
        return win;
    }

    // If all cells are filled and no winner
    @Override
    public boolean checkDrawCondition(Board board) {
        int size = board.getSize();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(board.getCell(i, j) == board.getEmptyCell()) {
                    return false;
                }
            }
        }
        return true;
    }

}
