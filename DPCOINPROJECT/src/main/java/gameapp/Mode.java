package gameapp;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Mode {
    private static final IntegerProperty counter = new SimpleIntegerProperty(2);
    private static final Label label = new Label(String.valueOf(counter.get()));
    private static final Root sp = Main.getStartPane();

    public static void turns(int mode) {
        counter.set(2);
        sp.getTitle().setText("Specify Number Of Coins");
        HBox hBox = CustomLayOuts.hBox();
        Button inc = new Button("+");
        Button dec = new Button("-");
        hBox.setSpacing(20);
        VBox.setMargin(hBox, new Insets(0, 0, 20, 0));
        inc.setStyle("-fx-pref-width: 50px; -fx-pref-height: 50px;");
        dec.setStyle("-fx-pref-width: 50px; -fx-pref-height: 50px;");

        counter.addListener((obs, oldVal, newVal) -> label.setText(String.valueOf(newVal)));

        inc.setOnAction(e -> {
            if (counter.get() < 100 && mode == 0) {
                counter.set(counter.get() + 2);
            }else if(mode == 1){
                counter.set(counter.get() + 2);
            }
        });

        dec.setOnAction(e -> {
            if (counter.get() > 2) {
                counter.set(counter.get() - 2);
            }
        });

        Button submit = getButton(mode);
        hBox.getChildren().addAll(dec, label, inc);
        sp.getVbox().getChildren().clear();
        sp.getVbox().getChildren().addAll(sp.getTitle(), hBox, submit, sp.getBack());
    }

    private static Button getButton(int mode) {
        Button submit = new Button("Confirm");
        submit.setOnAction(e ->{
            if(counter.get() <= 50 && mode == 0) {
                submit(counter.get(), mode);
            }else if(counter.get() > 50 && mode == 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Maximum number of coins is 50 for two players mode");
                alert.show();
            }
            if(counter.get()<=50 && mode == 1){
                submit(counter.get(), mode);
            }else if(counter.get()>50 && mode == 1){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirm your action");
                alert.setContentText("Too many coins, can't display all of them, would you like to continue with only showing the results?");

                // Show the alert and wait for user input
                alert.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        submit(counter.get(), mode);
                    } else if (response == ButtonType.NO) {
                        System.out.println("No");
                    } else {
                        System.out.println("Dialog closed without a selection.");
                    }
                });
            }
        });
        return submit;
    }
    public static void submit(int num,int mode) {
        sp.getTitle().setText("Choose Mode");
        Button manualButton = new Button("Manual");
        Button randomButton = new Button("Random");
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> sp.start());
        manualButton.setOnAction(e -> Manual.startManualInput(num, sp, counter, mode));
        randomButton.setOnAction(e -> Casual.generateRandomInput(num, mode));
        sp.getVbox().getChildren().clear();
        sp.getVbox().getChildren().add(sp.getTitle());
        sp.getVbox().getChildren().addAll(manualButton, randomButton, backButton);
    }

    public static void load() {
        Load.loadGame();
    }
}
