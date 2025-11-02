package LLDSnakeNLadderGame.strategy;

import LLDSnakeNLadderGame.entity.Board;
import LLDSnakeNLadderGame.entity.Ladder;
import LLDSnakeNLadderGame.entity.Snake;

import java.util.ArrayList;
import java.util.List;

// Custom Strategy - User provides count
public class CustomCountBoardSetupStrategy implements BoardSetupStrategy {
    private int numSnakes;
    private int numLadders;
    private boolean randomPositions;
    private List<Position> snakePositions;
    private List<Position> ladderPositions;

    // Simple Position class
    private static class Position {
        public final int start;
        public final int end;

        public Position(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public CustomCountBoardSetupStrategy(int snakes, int ladders, boolean random) {
        numSnakes = snakes;
        numLadders = ladders;
        randomPositions = random;
        snakePositions = new ArrayList<>();
        ladderPositions = new ArrayList<>();
    }

    public void addSnakePosition(int start, int end) {
        snakePositions.add(new Position(start, end));
    }

    public void addLadderPosition(int start, int end) {
        ladderPositions.add(new Position(start, end));
    }

    @Override
    public void setupBoard(Board board) {
        if(randomPositions) {
            // Random placement with user-defined counts
            int boardSize = board.getBoardSize();

            // Add snakes
            int snakesAdded = 0;
            while(snakesAdded < numSnakes) {
                int start = (int)(Math.random() * (boardSize - 10)) + 10;
                int end = (int)(Math.random() * (start - 1)) + 1;

                if(board.canAddEntity(start)) {
                    board.addEntity(new Snake(start, end));
                    snakesAdded++;
                }
            }

            // Add ladders
            int laddersAdded = 0;
            while(laddersAdded < numLadders) {
                int start = (int)(Math.random() * (boardSize - 10)) + 1;
                int end = (int)(Math.random() * (boardSize - start)) + start + 1;

                if(board.canAddEntity(start) && end < boardSize) {
                    board.addEntity(new Ladder(start, end));
                    laddersAdded++;
                }
            }
        }
        else {
            // User-defined positions
            for(Position pos : snakePositions) {
                if(board.canAddEntity(pos.start)) {
                    board.addEntity(new Snake(pos.start, pos.end));
                }
            }

            for(Position pos : ladderPositions) {
                if(board.canAddEntity(pos.start)) {
                    board.addEntity(new Ladder(pos.start, pos.end));
                }
            }
        }
    }
}