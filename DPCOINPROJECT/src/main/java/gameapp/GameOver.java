package gameapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOver {
    private Stage stage;
    private GameInfo gameInfo;

    public GameOver(GameInfo gameInfo, String winner,int score) {
        // Initialize the scoreboard UI in a new Stage
        this.gameInfo = gameInfo;
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Game Over");


        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);

        Button replayButton = new Button("Replay");
        Button closeButton = new Button("Close");

        // Define button actions
        replayButton.setOnAction(e -> replayGame());
        closeButton.setOnAction(e -> stage.close());
        if(winner.equalsIgnoreCase("player1")) {
            Image img = new Image(getClass().getResource("/img/" + winner + ".png").toExternalForm());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            layout.getChildren().addAll(new Label("Winner"), imageView, new Label(String.valueOf(gameInfo.getName1())),new Label(score + ""), replayButton, closeButton);
        }else if(winner.equalsIgnoreCase("player2")){
            Image img = new Image(getClass().getResource("/img/" + winner + ".png").toExternalForm());
            ImageView imageView = new ImageView(img);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            layout.getChildren().addAll(new Label("Winner"), imageView, new Label(String.valueOf(gameInfo.getName2())), new Label(score + ""),replayButton, closeButton);

        }else{
            layout.getChildren().addAll(new Label("Draw"),new Label(score + ""), replayButton, closeButton);

        }

        Scene scene = new Scene(layout, 300, 500);
        scene.getStylesheets().add(getClass().getResource("/css/game-over.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
    }

    // Method to show the GameOver scoreboard
    public void show() {
        stage.showAndWait();
    }

    // Replay action (reset game or notify other classes)
    private void replayGame() {
        PlayerSetup playerSetup = new PlayerSetup(gameInfo);
        playerSetup.replay(gameInfo);
        Main.getStartPane().setBottom(null);
        Main.getStartPane().setTop(null);
        stage.close();
    }
}
