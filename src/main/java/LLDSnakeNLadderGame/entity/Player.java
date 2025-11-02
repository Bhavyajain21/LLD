package LLDSnakeNLadderGame.entity;

public class Player {
    private int playerId;
    private String name;
    private int position;
    private int score;

    public Player(int playerId, String n) {
        this.playerId = playerId;
        name = n;
        position = 0;
        score = 0;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }
    public int getPosition() {
        return position;
    }
    public void setPosition(int pos) {
        position = pos;
    }
    public int getScore() {
        return score;
    }
    public void incrementScore() {
        score++;
    }

}
