package gameapp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.concurrent.Flow;

public class CoinLabel extends Label {

    private final int[] arr;
    private final int index;
    private final FlowPane mainBox;

    public CoinLabel(String text) {
        super(text);
        this.arr = new int[0];
        this.index = -1;
        this.mainBox = new FlowPane();
        this.getStyleClass().add("coin-label");
        initializeLabel();
    }
    public CoinLabel(String text, FlowPane mainBox, int[] arr, int index) {
        super(text);
        this.arr = arr;
        this.index = index;
        this.mainBox = mainBox;
        initializeLabel();
        configureMouseClickEvent();
    }


    private void initializeLabel() {
        this.getStyleClass().add("coin-label");
        this.setAlignment(Pos.CENTER);
        this.setContentDisplay(ContentDisplay.CENTER);
    }

    private void configureMouseClickEvent() {
        this.setOnMouseClicked(e -> showConfirmationDialog());
    }

    private void showConfirmationDialog() {
        HBox hbox = CustomLayOuts.hBox();
        Scene confirmation = new Scene(hbox, 600, 200);
        confirmation.getStylesheets().add(getClass().getResource("/css/confirmation-style.css").toExternalForm());
        Stage stage = createStage(confirmation);

        TextField tf = createTextField();
        Button editButton = createEditButton(tf, stage);
        Button deleteButton = createDeleteButton(stage);

        hbox.setSpacing(50);
        hbox.setStyle("-fx-background-color:rgb(224, 242, 249);");
        hbox.getChildren().addAll(editButton, tf, deleteButton);

        stage.show();
    }

    private Stage createStage(Scene confirmation) {
        Stage stage = new Stage();
        stage.setScene(confirmation);
        stage.setResizable(false);
        return stage;
    }

    private TextField createTextField() {
        TextField tf = new TextField(this.getText());
        tf.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            return newText.matches("-?\\d*") ? change : null;
        }));
        tf.setStyle("-fx-background-color: white;");
        return tf;
    }

    private Button createEditButton(TextField tf, Stage stage) {
        Button edit = new Button("Edit");
        edit.setOnAction(event -> {
            if(tf.getText().length() <= 4 && isDigit(tf.getText())) {
                updateLabelText(tf.getText());
                stage.close();
            }else if(tf.getText().length()>4){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You are only allowed to type 4 digits");
                alert.show();
            }else if(!isDigit(tf.getText())){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You are only allowed to enter numerical values");
                alert.show();
            }
        });
        return edit;
    }
    private boolean isDigit(String string){
        try{
            int n = Integer.parseInt(string);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
    private Button createDeleteButton(Stage stage) {
        Button del = new Button("Delete");
        del.setOnAction(event -> {
            deleteLabel();
            stage.close();
        });
        return del;
    }

    private void updateLabelText(String newText) {
        this.setText(newText);
        arr[index] = Integer.parseInt(newText);
    }

    private void deleteLabel() {
        mainBox.getChildren().remove(this);
        Manual.inputCount.set(Manual.inputCount.get() - 1);
        arr[index] = 0;  // Reset the array value at index
    }
}
