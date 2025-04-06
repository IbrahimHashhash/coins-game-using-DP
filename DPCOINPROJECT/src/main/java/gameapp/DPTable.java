package gameapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DPTable {

    public DPTable(int[][] dp, int[] coins) {
        Stage stage = new Stage();

        // Create a GridPane to layout the DP table
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);  // Set horizontal gap between cells
        gridPane.setVgap(10);  // Set vertical gap between cells

        buildGrid(dp, gridPane, coins);  // Populate the grid with the DP values and labels

        VBox vbox = new VBox();
        Button button = new Button("EXIT");
        button.setOnAction(e -> stage.close());  // Close the stage when clicked
        button.setStyle("-fx-pref-width: 150px;-fx-pref-height: 60px;-fx-border-radius: 50px;-fx-background-radius: 50px;-fx-background-color: red;-fx-text-fill: white;-fx-font-size: 20px;-fx-border-color: white;");

        vbox.setSpacing(20);  // Set spacing between elements in VBox
        Label label = new Label("DP Table");
        label.setStyle("-fx-text-fill: Orange;");
        vbox.getChildren().addAll(label, gridPane, button);

        // Set the background color of the VBox
        vbox.setStyle("-fx-background-color: linear-gradient(to right, rgba(42, 79, 139, 1), rgb(224, 242, 249));");
        vbox.setAlignment(Pos.CENTER);  // Center content in VBox

        Scene scene = new Scene(vbox, 780, 700);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    // This method populates the GridPane with labels displaying the DP table values and adds row/column headers
    private void buildGrid(int[][] dp, GridPane gridPane, int[] coins) {
        // Add column headers (coin values + position)
        for (int col = 0; col < coins.length; col++) {
            Label coinLabel = new Label("Pos: " + col + "\nCoin: " + coins[col]);
            coinLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-border-color: orange; -fx-border-width: 1px;-fx-background-color: orange;");
            coinLabel.setAlignment(Pos.CENTER);  // Center the text inside the label
            gridPane.add(coinLabel, col + 1, 0);  // Add label to the first row (column headers)
        }

        // Add row headers (position + coin value)
        for (int row = 0; row < dp.length; row++) {
            Label posLabel = new Label("Pos: " + row + "\nCoin: " + coins[row]);  // Use coins[row] for the row coin value
            posLabel.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-border-color: orange; -fx-border-width: 1px;-fx-background-color: orange;");
            posLabel.setAlignment(Pos.CENTER);  // Center the text inside the label
            gridPane.add(posLabel, 0, row + 1);  // Add label to the first column (row headers)
        }

        // Populate the DP table cells with values
        for (int row = 0; row < dp.length; row++) {
            for (int col = 0; col < dp[row].length; col++) {
                CoinLabel label = new CoinLabel(String.valueOf(dp[row][col]));
                label.setDisable(true);
                label.setStyle("-fx-font-size: 16px; -fx-padding: 10px; -fx-border-color: black; -fx-border-width: 1px;");
                gridPane.add(label, col + 1, row + 1);  // Add label to the DP table cells
            }
        }
    }
}
