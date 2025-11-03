package LLDTicTacToe.entity;

public class Player {
    private int playerId;
    private String name;
    private Symbol symbol;
    private int score;

    public Player(int playerId, String name, Symbol symbol) {
        this.playerId = playerId;
        this.name = name;
        this.symbol = symbol;
        score = 0;
    }

    public String getName() {
        return name;
    }
    public Symbol getSymbol() {
        return symbol;
    }

    public void incrementScore() {
        score++;
    }

    public int getScore() {
        return score;
    }
}
