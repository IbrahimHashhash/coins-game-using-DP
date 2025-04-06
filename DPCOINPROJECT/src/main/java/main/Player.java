package main;

public class Player {
    private int sum;  // Remove 'static' to make it instance-specific
    private String name;
    private static int score;

    public Player(String name) {
        this.name=name;
        // You can initialize the name here if needed
    }

    public void setScore(int score) {
        Player.score = score;
    }

    public int getScore() {
        return score;
    }

    public void incScore() {
        score++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sum(int num) {
        this.sum += num;  // Add to the player's score
    }

    public int getSum() {
        return sum;  // Return the current player's score
    }
}
