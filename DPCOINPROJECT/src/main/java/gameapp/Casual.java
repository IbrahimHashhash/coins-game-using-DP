package gameapp;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Random;

public class Casual {

    // This method generates the random input for the game setup
    public static void generateRandomInput(int numCoins, int mode) {
        // Create input fields and buttons for the user to select a range
        TextField zero = new TextField("0");
        zero.setMaxWidth(120);
        zero.setDisable(true);
        zero.setMaxHeight(60);
        TextField toNum = new TextField();
        toNum.setMaxWidth(120);
        toNum.setMaxHeight(60);
        validateInput(toNum); // Validate the user input
        Button button = new Button("Pick");
        button.setStyle("-fx-font-size: 16px;");
        button.setMaxWidth(100);

        // Create a layout for the input fields and button
        FlowPane fp = CustomLayOuts.fBox();
        fp.setHgap(30);
        fp.getChildren().addAll(zero,button,toNum);

        // Clear the existing UI components and set the new layout
        Main.getStartPane().getVbox().getChildren().clear();
        zero.setAlignment(Pos.CENTER);
        toNum.setAlignment(Pos.CENTER);
        VBox.setMargin(fp, new Insets(50));
        zero.setOpacity(0.5);
        Main.getStartPane().getVbox().getChildren().addAll(new Label("Pick the range"), fp, Main.getStartPane().getBack());

        // Set up button action to handle input and pick a range
        button.setOnAction(e -> {
            if(!toNum.getText().isEmpty()){
                int num = Integer.parseInt(toNum.getText());
                pickRange(numCoins, mode, num); // Call method to handle picking range
            }
        });
    }

    // This method handles the coin range selection and prepares for the game start
    public static void pickRange(int numCoins, int mode, int num) {
        FlowPane flowPane = CustomLayOuts.fBox();  // Create a new layout for the coins
        int[] arr = new int[numCoins];  // Array to store coin values
        Main.getStartPane().getVbox().getChildren().clear();  // Clear existing UI components

        Button start = new Button("Start");
        Main.getStartPane().getTitle().setText(null);

        // Check if there are too many coins
        if(numCoins > 50) {
            Main.getStartPane().getVbox().getChildren().addAll(Main.getStartPane().getTitle(), new Label("Too many coins"), start, Main.getStartPane().getBack());
        } else {
            Main.getStartPane().getVbox().getChildren().addAll(Main.getStartPane().getTitle(), flowPane, start, Main.getStartPane().getBack());
        }

        // Populate the coins with random values and display them
        populateCoins(numCoins, flowPane, start, Main.getStartPane(), arr, num);

        // Set action for the "Start" button based on the selected mode
        start.setOnAction(e -> {
            if (mode == 0) new PlayerSetup(flowPane);  // Player setup mode
            else if (mode == 1) new ComputerMode(arr, flowPane);  // Computer mode
        });
    }

    // This method validates that the input is a number
    private static void validateInput(TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            // Only allow numbers to be entered
            if (newText.matches("-?\\d*")) {
                return change;
            }
            return null;
        }));
    }

    // This method populates the coin values and displays them on the UI
    private static void populateCoins(int numCoins, FlowPane flowPane, Button start, Root sp, int[] arr, int range) {
        Label label = new Label(numCoins + " Coins");
        sp.getVbox().getChildren().add(2, label);  // Add label to the UI
        label.setStyle("-fx-font-size: 28px;-fx-text-fill: rgb(197, 158, 1);");
        Random random = new Random();  // Random number generator for coin values

        // Generate random coin values and add them to the flowPane
        for (int i = 0; i < numCoins; i++) {
            int coinVal = random.nextInt(range / 2) * 2 + 1;  // Random odd coin value
            flowPane.getChildren().add(new CoinLabel(String.valueOf(coinVal)));
            arr[i] = coinVal;  // Store coin value in the array
        }

        sp.getTitle().setText("You are ready to begin");  // Update the title
        start.setStyle("-fx-border-color: lightGreen;-fx-background-color: lightGreen;-fx-text-fill: white;");  // Style the "Start" button
    }
}
