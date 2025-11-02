package LLDSnakeNLadderGame.entity;

public class Ladder extends BoardEntity{
    public Ladder(int startPosition, int endPosition) {
        super(startPosition, endPosition);
        if(endPosition <= startPosition) {
            System.out.println("Invalid ladder! End must be greater than start.");
        }
    }

    @Override
    public void display() {
        System.out.println("Ladder from " + startPosition + " to " + endPosition);
    }

    @Override
    public String name() {
        return "LADDER";
    }
}
