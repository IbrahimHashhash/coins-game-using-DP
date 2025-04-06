package gameapp;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import main.Computer;
import main.Dynamic;

public class ComputerMode {
    // Style definitions for coin buttons
    private static final String COIN_SELECTED_STYLE1 = "-fx-background-color: #FF6347; -fx-border-color: #FFC1C1; -fx-text-fill: white;";
    private static final String COIN_SELECTED_STYLE2 = "-fx-background-color: #32CD32; -fx-border-color: #90EE90; -fx-text-fill: white;";
    private static final String COIN_SELECTED_STYLE11 = "-fx-background-color: #FF6347; -fx-border-color: #FFC1C1; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-height: 40px; -fx-pref-width: 40px;";
    private static final String COIN_SELECTED_STYLE22 = "-fx-background-color: #32CD32; -fx-border-color: #90EE90; -fx-text-fill: white; -fx-font-size: 14px; -fx-pref-height: 40px; -fx-pref-width: 40px;";

    // Declare variables for game logic, players, and UI elements
    private final Dynamic dynamic;
    private Computer comp1;
    private Computer comp2;
    private int start;
    private int end;
    private int turn1;
    private int turn2;
    private boolean isComp1Turn = true; // Keeps track of whose turn it is
    private final int[] arr;
    private PlayerPane p1 = new PlayerPane("player1");
    private PlayerPane p2 = new PlayerPane("player2");
    private final FlowPane coinLabel;
    private final Button move = new Button("Play");
    private final Button dpTable = new Button("DP Table");

    // Constructor for setting up the game
    public ComputerMode(int[] arr, FlowPane coinLabel) {
        this.arr = arr;
        this.coinLabel = coinLabel;
        dynamic = new Dynamic(arr);
        end = coinLabel.getChildren().size() - 1; // Set the end coin position
        // Check if there are too many coins
        if(coinLabel.getChildren().size() > 50) {
            Main.getStartPane().setCenter(new Label("Too many coins"));
        } else {
            Main.getStartPane().setCenter(coinLabel);
        }
        initializeGame(); // Initialize game logic and players
        initializeButtons(); // Set up button actions
        initializeLayout(); // Set up the game layout and UI
    }

    // Initializes the game, including the turn orders for each player
    private void initializeGame() {
        // Get the optimized turn order from dynamic programming
        int[] turns = dynamic.getTurns();
        int halfLength = turns.length / 2;

        // Allocate turn arrays for both players
        int[] turns1 = new int[halfLength];
        int[] turns2 = new int[halfLength];

        // Assign turns to players
        System.arraycopy(turns, 0, turns1, 0, halfLength); // Player 1's turns
        for (int i = 0; i < halfLength; i++) {
            turns2[i] = turns[turns.length - 1 - i]; // Player 2's turns in reverse order
        }

        // Initialize players with their respective turns
        this.comp1 = new Computer("Player1", turns1);
        this.comp2 = new Computer("Player2", turns2);
    }

    // Initializes the buttons and their actions
    private void initializeButtons() {
        move.setOnAction(e -> startAutomaticGame()); // Action for "Next Move" button
        dpTable.setOnAction(e -> new DPTable(dynamic.getDpTable(),arr)); // Action for DP Table button
    }
    // Handles the move logic for each player's turn
    private void startAutomaticGame() {
        new Thread(() -> { // Run the game in a separate thread to avoid blocking the UI
            try {
                while (turn1 < comp1.getTurns().length || turn2 < comp2.getTurns().length) {
                    // Update the UI on the JavaFX Application Thread
                    Platform.runLater(() -> handleMove()); // Execute one move on the UI thread

                    // Pause for 1 second before the next move
                    Thread.sleep(1000); // 1000 milliseconds = 1 second
                }
                System.out.println("All moves completed.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start(); // Start the thread
    }

    private void handleMove() {
        // Check if both players have completed all their turns
        if (turn1 >= comp1.getTurns().length && turn2 >= comp2.getTurns().length) {
            System.out.println("All moves completed.");
            return;
        }

        // Get the current start and end coin values
        int endCoin = Integer.parseInt(((CoinLabel) coinLabel.getChildren().get(end)).getText());
        int startCoin = Integer.parseInt(((CoinLabel) coinLabel.getChildren().get(start)).getText());

        // Process the current player's move
        if (isComp1Turn && turn1 < comp1.getTurns().length) {
            handlePlayerTurn(comp1, startCoin, endCoin, COIN_SELECTED_STYLE1, true);
            isComp1Turn = false; // Switch to Player 2's turn
        } else if (!isComp1Turn && turn2 < comp2.getTurns().length) {
            handlePlayerTurn(comp2, startCoin, endCoin, COIN_SELECTED_STYLE2, false);
            isComp1Turn = true; // Switch to Player 1's turn
        } else {
            System.err.println("No valid turns left for the current player.");
        }
    }

    // Handles the logic for processing a single player's turn
    private void handlePlayerTurn(Computer player, int startCoin, int endCoin, String style, boolean isComp1) {
        int[] playerTurns = player.getTurns();
        int currentTurnValue = isComp1 ? playerTurns[turn1] : playerTurns[turn2];

        // Check if the current turn matches either the start or end coin
        if (currentTurnValue == startCoin) {
            // Select the start coin and move the start pointer inward
            coinLabel.getChildren().get(start).setStyle(style);
            start++;
        } else if (currentTurnValue == endCoin) {
            // Select the end coin and move the end pointer inward
            coinLabel.getChildren().get(end).setStyle(style);
            end--;
        }

        // Increment the turn for the current player
        if (isComp1) {
            turn1++;
        } else {
            turn2++;
        }

        // Check if all moves are completed
        if (turn1 >= comp1.getTurns().length && turn2 >= comp2.getTurns().length) {
            System.out.println("All moves completed.");
        }
    }

    // Sets up the layout of the game, including the UI elements
    private void initializeLayout() {
        p1.setStyle("-fx-background-color: orange;-fx-border-color: orange;");
        p1.setDisable(true); // Disable the player pane for Player 1
        p2.setDisable(true); // Disable the player pane for Player 2

        FlowPane bottom = CustomLayOuts.fBox();
        bottom.getChildren().addAll(move, dpTable); // Add buttons to the bottom pane
        BorderPane.setMargin(bottom, new Insets(20));
        Main.getStartPane().setBottom(bottom); // Set the bottom pane

        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setVgap(10);
        flowPane.setHgap(30);

        // Set up DP Table button action
        dpTable.setOnAction(e -> new DPTable(dynamic.getDpTable(),arr));

        p1.setScore(comp1.result());
        p2.setScore(comp2.result());

        // Create FlowPanes for displaying turn sequences for each player
        FlowPane fp1 = createTurnFlowPane(comp1, COIN_SELECTED_STYLE11);
        FlowPane fp2 = createTurnFlowPane(comp2, COIN_SELECTED_STYLE22);

        MenuPane menuPane = new MenuPane(this);
        flowPane.getChildren().addAll(fp1, p1, menuPane, p2, fp2); // Add UI elements to the main pane
        BorderPane.setMargin(flowPane, new Insets(20));
        Main.getStartPane().setTop(flowPane); // Set the top pane
    }

    // Creates a FlowPane displaying the turn sequence for a given player
    private FlowPane createTurnFlowPane(Computer player, String style) {
        FlowPane fp = CustomLayOuts.fBox();
        for (int turn : player.getTurns()) {
            CoinLabel coinLabel2 = new CoinLabel(turn + "");
            coinLabel2.setStyle(style);
            fp.getChildren().add(coinLabel2);
        }
        return fp;
    }

    // Returns the array of coins
    public int[] getArray() {
        return arr;
    }
}
