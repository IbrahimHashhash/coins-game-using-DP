package gameapp;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Root extends BorderPane {
    private final VBox vBox = CustomLayOuts.vBox();
    private final Label title = new Label();
    private final Button back = new Button("Back");
    public Root(){
        VBox.setMargin(title, new Insets(0, 0,20,0));
        title.setId("title");
        this.setCenter(vBox);
    }

    public void start(){
        title.setText("Select Game Mode");
        Button twoPlayer = new Button("2 Player");
        Button Bot = new Button("Vs Bot");
        vBox.getChildren().clear();
        vBox.getChildren().addAll(title,twoPlayer,Bot,back);
        twoPlayer.setOnAction(e-> Mode.turns(0));
        Bot.setOnAction(e-> Mode.turns(1));
        back.setOnAction(e->setMainMenu());
    }
    public void help(){
        vBox.getChildren().clear();
        Text guide = new Text();
        guide.setId("guide");
        guide.setText("Optimal Game Solution:\n" +
                "\n" +
                "1- Two players take alternate turns.\n" +
                "2- The coins are arranged in a row, and the number of coins is even.\n" +
                "3- On each turn, a player can pick either the first or last coin from the row.\n" +
                "4- Both players play optimally, aiming to maximize their total collected value.\n" +
                "5- The objective for the first player is to guarantee the highest possible score.\n" +
                "6- The game ends when all coins are taken.\n" +
                "7- the goal is for the first player to secure the maximum possible amount.");
        VBox.setMargin(guide,new Insets(0,0,30,0));
        vBox.getChildren().addAll(guide,back);

    }
    public VBox getVbox(){
        return vBox;
    }
    public Button getBack(){
        return back;
    }

    public Label getTitle() {
        return title;
    }

    public void setMainMenu(){
        vBox.getChildren().clear();
        vBox.getChildren().addAll(Main.mainMenu.getButtons());
        this.setCenter(vBox);
        this.setBottom(null);
        this.setTop(null);
    }

}
