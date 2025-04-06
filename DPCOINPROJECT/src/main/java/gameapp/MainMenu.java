package gameapp;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private final Root root; // Root layout for the main menu
    private final Button[] buttons; // Buttons in the main menu
    private final Scene scene; // Scene representing the main menu

    public MainMenu(Root root) {
        this.root = root;
        this.buttons = createButtons(); // Initialize buttons
        this.scene = new Scene(root, 600, 400); // Set scene size
        setupBackground(); // Configure background image
        setupButtons(); // Configure button actions and layout
    }

    // Configures the background image for the main menu
    private void setupBackground() {
        Image backgroundImage = new Image(getClass().getResource("/img/bg.gif").toExternalForm());
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setOpacity(0.55); // Adjust transparency
        imageView.fitWidthProperty().bind(scene.widthProperty()); // Bind width to scene width
        imageView.fitHeightProperty().bind(scene.heightProperty()); // Bind height to scene height
        root.getChildren().add(0, imageView); // Add the background image to the root
    }

    // Creates and styles the main menu buttons
    private Button[] createButtons() {
        Button[] buttons = new Button[4];
        buttons[0] = new Button("Start"); // Button to start the game
        buttons[1] = new Button("Load"); // Button to load a saved game
        buttons[2] = new Button("Help"); // Button for help menu
        buttons[3] = new Button("Exit"); // Button to exit the game

        // Style the exit button
        buttons[3].setStyle("-fx-background-color: rgba(82, 79, 154, 1);-fx-border-color:None;-fx-text-fill:white;");
        return buttons;
    }

    // Adds buttons to the layout and sets their actions
    private void setupButtons() {
        root.getVbox().getChildren().addAll(buttons); // Add buttons to the VBox

        // Action for the back button to restore main menu buttons
        root.getBack().setOnAction(e -> {
            root.getVbox().getChildren().clear();
            root.getVbox().getChildren().addAll(buttons);
        });

        // Set actions for the main menu buttons
        buttons[0].setOnAction(e -> root.start()); // Start game
        buttons[1].setOnAction(e -> Mode.load()); // Load game
        buttons[2].setOnAction(e -> root.help()); // Open help menu
    }

    // Returns the array of buttons
    public Button[] getButtons() {
        return buttons;
    }

    // Configures the exit button to display a confirmation dialog
    public void setupExitButton(Stage primaryStage) {
        buttons[3].setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to exit the game?");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) { // Close application on confirmation
                    primaryStage.close();
                } else if (response == ButtonType.NO) { // Handle "No" response
                    System.out.println("No");
                } else { // Handle dialog closure without selection
                    System.out.println("Dialog closed without a selection.");
                }
            });
        });
    }

    // Returns the scene for the main menu
    public Scene getScene() {
        return scene;
    }
}
