package gameapp;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
public class PlayerSetup extends VBox {
    PlayerPane p1 = new PlayerPane("player1");
    PlayerPane p2 = new PlayerPane("player2");
    String name1;
    String name2;
    FlowPane coinBox;
    public PlayerSetup(FlowPane coinBox){
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        this.coinBox = coinBox;
        pickNames();

    }
    public PlayerSetup(GameInfo gameInfo){
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);
        replay(gameInfo);
    }


    public void pick(){
        this.getChildren().clear();
        this.setSpacing(20);
        Main.getStartPane().getTitle().setText("Pick Who Starts First");
        Main.getStartPane().setCenter(this);
        VBox.setMargin(p1,new Insets(0,10,0,10));
        VBox.setMargin(p2,new Insets(0,10,0,10));
        this.getChildren().addAll(Main.getStartPane().getTitle(),p1,p2,Main.getStartPane().getBack());
        Label n1 = new Label(name1);
        Label n2 = new Label(name2);
        n1.setStyle("-fx-font-weight: normal; -fx-font-size: 24px;");
        n2.setStyle("-fx-font-weight: normal; -fx-font-size: 24px;");
        p1.setRight(n1);
        p2.setRight(n2);
            p1.setOnMouseClicked(mouseEvent -> {
                TwoPlayer twoPlayer = new TwoPlayer(coinBox, name1, name2, 0);
            });
            p2.setOnMouseClicked(mouseEvent -> {
                TwoPlayer twoPlayer = new TwoPlayer(coinBox, name1, name2, 1);
            });

    }
    public void replay(GameInfo gameInfo){
        this.getChildren().clear();
        this.setSpacing(20);
        gameInfo.getRoot().getTitle().setText("Pick Who Starts First");
        gameInfo.getRoot().setCenter(this);
        VBox.setMargin(p1,new Insets(0,10,0,10));
        VBox.setMargin(p2,new Insets(0,10,0,10));
        this.getChildren().addAll(gameInfo.getRoot().getTitle(),p1,p2,gameInfo.getRoot().getBack());
        Label n1 = new Label(gameInfo.getName1());
        Label n2 = new Label(gameInfo.getName2());
        n1.setStyle("-fx-font-weight: normal; -fx-font-size: 24px;");
        n2.setStyle("-fx-font-weight: normal; -fx-font-size: 24px;");
        p1.setRight(n1);
        p2.setRight(n2);

        p1.setOnMouseClicked(mouseEvent -> {
            TwoPlayer twoPlayer = new TwoPlayer(gameInfo.getCoinBox(), gameInfo.getName1(), gameInfo.getName2(), 0);
        });
        p2.setOnMouseClicked(mouseEvent -> {
            TwoPlayer twoPlayer = new TwoPlayer(gameInfo.getCoinBox(), gameInfo.getName1(), gameInfo.getName2(), 1);
        });

    }

    public void pickNames(){
        Main.getStartPane().setCenter(this);
        Label label1 = new Label("P1 Name: ");
        Label label2 = new Label("P2 Name: ");
        Button button = new Button("Go");

        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        tf1.setStyle("-fx-pref-height:40px;-fx-background-color: white;-fx-border-width:1px;-fx-border-radius:4px;");
        tf2.setStyle("-fx-pref-height:40px;-fx-background-color: white;-fx-border-width:1px;-fx-border-radius:4px;");
        button.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");
        button.setOnAction(e->{
            this.name1= tf1.getText();
            this.name2= tf2.getText();
            pick();

        });
        label1.setGraphic(tf1);
        label1.setContentDisplay(ContentDisplay.RIGHT);
        label2.setGraphic(tf2);
        label2.setContentDisplay(ContentDisplay.RIGHT);
        button.setDisable(true);
        Label error = new Label("Can't have the same names");
        error.setStyle("-fx-font-size: 16px;-fx-text-fill: red;");

        addTextFieldListener(tf1,tf2,button,error);
        this.getChildren().addAll(label1,label2,button,Main.getStartPane().getBack());

    }

    public void addTextFieldListener(TextField textField1, TextField textField2, Button button,Label error) {
        // Create a listener to check both TextFields
        ChangeListener<String> textFieldListener = (observable, oldValue, newValue) -> {
            // Check if both TextFields are not empty
            if (!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
                if(!(textField1.getText().equalsIgnoreCase(textField2.getText()))) {
                    if(textField1.getText().length() < 26 && textField2.getText().length() < 26) {
                        this.getChildren().remove(error);
                        button.setDisable(false);  // Enable button if both TextFields are not empty
                        button.setStyle("-fx-background-color: lightGreen;-fx-border-color:lightGreen;-fx-text-fill:white;");
                    }else{
                        this.getChildren().remove(error);
                        error.setText("Name too long");
                        this.getChildren().add(2,error);
                        button.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");
                        button.setDisable(true);   // Disable button if any TextField is empty

                    }
                }else{
                    this.getChildren().remove(error);
                    error.setText("Can't have the same names");
                    this.getChildren().add(2,error);
                    button.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");
                    button.setDisable(true);   // Disable button if any TextField is empty

                }
            } else {
                button.setStyle("-fx-border-color: rgba(42, 79, 139,1);-fx-background-color: rgb(224, 242, 249);-fx-text-fill: rgba(42, 79, 139, 1);-fx-opacity: 0.4;");
                button.setDisable(true);   // Disable button if any TextField is empty
                this.getChildren().remove(error);
            }
        };

        // Add the listener to both TextFields
        textField1.textProperty().addListener(textFieldListener);
        textField2.textProperty().addListener(textFieldListener);

        // Initially disable the button
        button.setDisable(true);
    }
}
