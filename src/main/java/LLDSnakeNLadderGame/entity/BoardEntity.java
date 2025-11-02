package LLDSnakeNLadderGame.entity;

public abstract class BoardEntity {
    protected int startPosition;
    protected int endPosition;

    public BoardEntity(int startPosition, int endPosition) {
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getStart() {
        return startPosition;
    }

    public int getEnd() {
        return endPosition;
    }

    public abstract void display();
    public abstract String name();
}
