package LLDSnakeNLadderGame.strategy.Rules;

import LLDSnakeNLadderGame.entity.Board;
import LLDSnakeNLadderGame.entity.BoardEntity;

public class StandardSnakeAndLadderRule implements SnakeAndLadderRule{
    @Override
    public boolean isValidMove(int currentPosition, int diceValue, int boardSize) {
        return (currentPosition + diceValue) <= boardSize;
    }

    @Override
    public int calculateNewPosition(int currentPosition, int diceValue, Board board) {
        int newPos = currentPosition + diceValue;
        BoardEntity entity = board.getEntity(newPos);

        if(entity != null) {
            return entity.getEnd();
        }
        return newPos;
    }

    @Override
    public boolean checkWinCondition(int position, int boardSize) {
        return position == boardSize;
    }
}
