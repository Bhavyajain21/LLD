package LLDSnakeNLadderGame.strategy;

import LLDSnakeNLadderGame.entity.*;
import LLDSnakeNLadderGame.enums.Difficulty;

public class RandomBoardSetupStrategy implements BoardSetupStrategy{
    private Difficulty difficulty;

    public RandomBoardSetupStrategy(Difficulty d) {
        difficulty = d;
    }

    private void setupWithProbability(Board board, double snakeProbability) {
        int boardSize = board.getBoardSize();
        int totalEntities = boardSize / 10; // Roughly 10% of board has entities

        for(int i=0;i<totalEntities;i++){
            double prob = Math.random();
            if(prob<snakeProbability){
                int attempts = 0;
                while(attempts < 50) {
                    int start = (int)(Math.random() * (boardSize - 10)) + 10;
                    int end = (int)(Math.random() * (start - 1)) + 1;

                    if(board.canAddEntity(start)) {
                        board.addEntity(new Snake(start, end));
                        break;
                    }
                    attempts++;
                }
            }
            else{
                int attempts = 0;
                while(attempts<50) {
                    int start = (int)(Math.random()*(boardSize-10))+1;
                    int end = (int)(Math.random() * (boardSize-start)) + (start + 1);
                    if(board.canAddEntity(start)) {
                        board.addEntity(new Ladder(start, end));
                        break;
                    }
                    attempts++;
                }
            }
        }
    }

    @Override
    public void setupBoard(Board board) {
        // Implement random board setup logic here
        switch(difficulty){
            case EASY:
                setupWithProbability(board, 0.3); // 30% snakes, 70% ladders
                break;
            case MEDIUM:
                setupWithProbability(board, 0.5); // 50% snakes, 50% ladders
                break;
            case HARD:
                setupWithProbability(board, 0.7); // 70% snakes, 30% ladders
                break;
        }
    }
}
