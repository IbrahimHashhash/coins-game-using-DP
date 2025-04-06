package gameapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PlayerPane extends BorderPane {
    // IntegerProperty for score, so it can be updated dynamically
    private IntegerProperty score = new SimpleIntegerProperty(0); // Initial score set to 0
    private String playerProfile;
    private String name;

    public PlayerPane(String playerProfile) {
        this.playerProfile=playerProfile;
        initialize();
    }
    public void initialize(){
        // Set the style of the PlayerPane
        this.getStyleClass().add("player-pane");
        // Load the image of the player
        Image img = new Image(getClass().getResource("/img/" + playerProfile + ".png").toExternalForm());

        // Create labels for the player name and score
        Label label = new Label(playerProfile);
        label.setStyle("-fx-font-size: 20px; -text-fill: black;");

        // Create the score label, binding its text to the IntegerProperty score
        Label scoreLabel = new Label();
        scoreLabel.setStyle("-fx-font-size: 15px; -text-fill: black;");
        scoreLabel.textProperty().bind(score.asString("Score: %d"));

        // Create an ImageView for the player image
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(100);  // Set the width to 100px
        imageView.setFitHeight(100); // Set the height to 100px

        // Set the position of the labels and image in the BorderPane
        VBox.setMargin(label, new Insets(10, 10, 10, 10));
        VBox.setMargin(scoreLabel, new Insets(10, 10, 10, 10));
        this.setTop(label);
        this.setBottom(scoreLabel);
        this.setCenter(imageView);

    }

    public void setScore(int newScore) {
        score.set(newScore);  // Updates the IntegerProperty score
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score.get();
    }
}
