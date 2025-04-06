package gameapp;

import javafx.geometry.Pos;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;

public class CustomLayOuts {
    public static VBox vBox(){
        VBox vBox = new VBox();
        vBox.setSpacing(25);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
    public static HBox hBox(){
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        return hBox;
    }
    public static FlowPane fBox(){
        FlowPane flowPane = new FlowPane();
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setHgap(10);
        flowPane.setVgap(5);
        return flowPane;
    }

}
