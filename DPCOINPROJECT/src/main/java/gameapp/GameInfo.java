package gameapp;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class GameInfo {
    private final FlowPane coinBox;
    private final Root root;
    private final String name1;
    private final String name2;
    private final int turn;
    // Constructor to initialize the instance variables
    public GameInfo(FlowPane coinBox, Root root, String name1, String name2, int turn) {
        this.coinBox = coinBox;
        this.root = root;
        this.name1 = name1;
        this.name2 = name2;
        this.turn = turn;
    }

    // Getters for each variable
    public FlowPane getCoinBox() {
        return coinBox;
    }

    public Root getRoot() {
        return root;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }

    public int getTurn() {
        return turn;
    }
}
