package gameapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

public class Manual {
    // Tracks the number of inputs entered by the user
    public static IntegerProperty inputCount = new SimpleIntegerProperty(0);

    // Initializes the manual input process for coin values
    public static void startManualInput(int numCoins, Root sp, IntegerProperty counter, int mode) {
        Button start = new Button("Start Game"); // Button to start the game
        start.setId("start-game");
        start.setDisable(true); // Disabled until all inputs are added

        inputCount.set(0); // Reset the input counter
        HBox hBox = CustomLayOuts.hBox();
        hBox.setSpacing(10);

        Button btn = new Button("Add"); // Button to add a coin value
        btn.setStyle("-fx-pref-width: 50px; -fx-pref-height: 50px;");

        TextField tf = new TextField(); // Input field for coin values
        tf.setStyle("-fx-pref-width: 150px; -fx-pref-height: 50px;");
        tf.setAlignment(Pos.CENTER);

        sp.getTitle().setText("Enter coin values"); // Update title
        hBox.getChildren().addAll(tf, btn);

        FlowPane labelBox = CustomLayOuts.fBox(); // Container for coin labels
        sp.getVbox().getChildren().clear();
        int[] coins = new int[numCoins]; // Array to store coin values

        Label label1 = new Label(String.valueOf(inputCount.get())); // Display the input count
        label1.setStyle("-fx-font-size: 40px; -fx-text-fill: Green;");
        labelBox.getChildren().add(label1);

        // Adjust layout based on the number of coins
        if (numCoins > 50) {
            sp.getVbox().getChildren().addAll(sp.getTitle(), hBox, label1, start, sp.getBack());
        } else {
            sp.getVbox().getChildren().addAll(sp.getTitle(), hBox, label1, labelBox, start, sp.getBack());
        }

        // Action for the start button
        start.setOnAction(e -> {
            if (mode == 0) new PlayerSetup(labelBox); // Setup for player vs. player mode
            else new ComputerMode(coins, labelBox); // Setup for player vs. computer mode
        });

        updateStartButtonOpacity(start, inputCount, counter, tf, btn);
        start.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");

        addInputCountListener(label1, counter, tf, btn, start); // Listen for input count changes
        addButtonActionListener(coins, tf, btn, counter, labelBox); // Add action for the "Add" button
        validateInput(tf); // Validate text field input
    }

    // Validates the input to accept only numeric values
    private static void validateInput(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return newText.matches("-?\\d*") ? change : null; // Allow only numbers
        }));
    }

    // Updates the label and enables/disables the start button based on input count
    private static void addInputCountListener(Label label1, IntegerProperty counter, TextField tf, Button btn, Button start) {
        inputCount.addListener((obs, oldVal, newVal) -> {
            label1.setText(String.valueOf(newVal)); // Update the count display
            updateStartButtonOpacity(start, inputCount, counter, tf, btn);
        });
    }

    // Controls the appearance and state of the start button
    private static void updateStartButtonOpacity(Button start, IntegerProperty inputCount, IntegerProperty counter, TextField tf, Button btn) {
        if (inputCount.get() == counter.get()) {
            start.setDisable(false);
            tf.setStyle("-fx-pref-width: 150px; -fx-pref-height: 50px; -fx-opacity: 0.2;");
            btn.setStyle("-fx-pref-width: 150px; -fx-pref-height: 50px; -fx-opacity: 0.2;");
            start.setStyle("-fx-background-color: lightGreen;-fx-border-color:lightGreen;-fx-text-fill:white;");
            tf.setEditable(false);
            btn.setDisable(true);
        } else {
            start.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");
            start.setDisable(true);
            btn.setStyle("-fx-pref-width: 150px; -fx-pref-height: 50px;");
            tf.setStyle("-fx-pref-width: 150px; -fx-pref-height: 50px;");
            tf.setEditable(true);
            btn.setDisable(false);
        }
    }

    // Handles the "Add" button action to add a coin value to the list
    private static void addButtonActionListener(int[] coins, TextField tf, Button btn, IntegerProperty counter, FlowPane labelBox) {
        btn.setOnAction(e -> {
            if (inputCount.get() < counter.get() && !tf.getText().isEmpty()) {
                coins[inputCount.get()] = Integer.parseInt(tf.getText()); // Add coin value
                CoinLabel newLabel = new CoinLabel(tf.getText(), labelBox, coins, inputCount.get()); // Create a label
                labelBox.getChildren().add(newLabel); // Add label to the UI
                inputCount.set(inputCount.get() + 1); // Increment input count
                tf.clear(); // Clear the input field
            }
        });
    }
}
