package LLDSnakeNLadderGame.entity;

public class Snake extends BoardEntity{
    public Snake(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if(endPosition >= startPosition) {
            System.out.println("Invalid snake! End must be less than start.");
        }
    }

    @Override
    public void display() {
        System.out.println("Snake from " + startPosition + " to " + endPosition);
    }

    @Override
    public String name() {
        return "SNAKE";
    }
}
