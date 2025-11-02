package LLDSnakeNLadderGame.strategy;

import LLDSnakeNLadderGame.entity.*;

// Standard Board Strategy - Traditional Snake & Ladder positions
public class StandardBoardSetupStrategy implements BoardSetupStrategy {
    @Override
    public void setupBoard(Board board) {
        // Standard board setup for 10x10 board
        if(board.getBoardSize() != 100) {
            System.out.println("Standard setup only works for 10x10 board!");
            return;
        }

        // Standard snake positions (based on traditional board)
        board.addEntity(new Snake(99,54));
        board.addEntity(new Snake(95, 75));
        board.addEntity(new Snake(92, 88));
        board.addEntity(new Snake(89, 68));
        board.addEntity(new Snake(74, 53));
        board.addEntity(new Snake(64, 60));
        board.addEntity(new Snake(62, 19));
        board.addEntity(new Snake(49, 11));
        board.addEntity(new Snake(46, 25));
        board.addEntity(new Snake(16, 6));

        // Standard ladder positions
        board.addEntity(new Ladder(2, 38));
        board.addEntity(new Ladder(7, 14));
        board.addEntity(new Ladder(8, 31));
        board.addEntity(new Ladder(15, 26));
        board.addEntity(new Ladder(21, 42));
        board.addEntity(new Ladder(28, 84));
        board.addEntity(new Ladder(36, 44));
        board.addEntity(new Ladder(51, 67));
        board.addEntity(new Ladder(71, 91));
        board.addEntity(new Ladder(78, 98));
        board.addEntity(new Ladder(87, 94));
    }
}
