package LLDSnakeNLadderGame.entity;

import LLDSnakeNLadderGame.strategy.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private List<BoardEntity> snakesAndLadders;
    private Map<Integer, BoardEntity> boardEntities;

    public Board(int s) {
        size = s*s;
        snakesAndLadders = new ArrayList<>();
        boardEntities = new HashMap<>();
    }

    public boolean canAddEntity(int position) {
        return !boardEntities.containsKey(position);
    }

    public void addEntity(BoardEntity entity) {
        if(canAddEntity(entity.getStart())){
            snakesAndLadders.add(entity);
            boardEntities.put(entity.getStart(),  entity);
        }
    }

    public void setupBoard(BoardSetupStrategy boardSetupStrategy) {
        boardSetupStrategy.setupBoard(this);
    }

    public BoardEntity getEntity(int position) {
        return boardEntities.get(position);
    }

    public int getBoardSize() {
        return size;
    }

    public void display() {
        System.out.println("\n=== Board Configuration ===");
        System.out.println("Board Size: " + size + " cells");

        int snakeCount = 0;
        int ladderCount = 0;
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) snakeCount++;
            else ladderCount++;
        }

        System.out.println("\nSnakes: " + snakeCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("SNAKE")) {
                entity.display();
            }
        }

        System.out.println("\nLadders: " + ladderCount);
        for(BoardEntity entity : snakesAndLadders) {
            if(entity.name().equals("LADDER")) {
                entity.display();
            }
        }
        System.out.println("=========================");
    }
}
