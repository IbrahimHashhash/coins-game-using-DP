package main;

public class Computer extends Player {
    private int[] turns;
    private int result;
    public Computer(String name,int[] turns) {
        super(name);
        this.turns = turns;
    }

    public void setTurns(int[] turns) {
        this.turns = turns;
    }

    public int[] getTurns() {
        return turns;
    }
    public int result(){
        for (int turn : turns) {
            result += turn;
        }
        return result;
    }
}
