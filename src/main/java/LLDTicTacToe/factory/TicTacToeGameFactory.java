package LLDTicTacToe.factory;

import LLDTicTacToe.Game;
import LLDTicTacToe.enums.GameType;

public class TicTacToeGameFactory {
    public static Game createGame(GameType gt, int boardSize) {
        if(GameType.STANDARD == gt) {
            return new Game(boardSize);
        }
        return null;
    }
}
