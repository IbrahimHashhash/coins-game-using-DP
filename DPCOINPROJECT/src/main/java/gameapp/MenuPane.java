package gameapp;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class MenuPane extends VBox {
    public MenuPane(GameInfo gameInfo){
        Button main = new Button("Main Menu");
        Button replay = new Button("Replay");
        main.setStyle("-fx-pref-width: 100px;-fx-pref-height:40px;-fx-font-size: 12px;");
        replay.setStyle("-fx-pref-width: 100px;-fx-pref-height:40px;-fx-font-size: 12px;");
        this.getChildren().addAll(main,replay);
        replay.setOnAction(e->{
            new TwoPlayer(gameInfo.getCoinBox(),gameInfo.getName1(),gameInfo.getName2(),gameInfo.getTurn());
            gameInfo.getRoot().setBottom(null);
        });
        main.setOnAction(e->gameInfo.getRoot().setMainMenu());
        initialize();
    }
    public void initialize(){
        this.setSpacing(4);
        this.setAlignment(Pos.CENTER);
        this.setMaxHeight(200);
        this.setMaxWidth(200);

    }
    public MenuPane(ComputerMode computerMode){
        Button main = new Button("Main Menu");
        Button replay = new Button("Replay");
        main.setStyle("-fx-pref-width: 100px;-fx-pref-height:40px;-fx-font-size: 12px;");
        replay.setStyle("-fx-pref-width: 100px;-fx-pref-height:40px;-fx-font-size: 12px;");
        this.getChildren().addAll(main,replay);
        FlowPane fp = CustomLayOuts.fBox();

        for(int i=0;i<computerMode.getArray().length;i++){
            fp.getChildren().add(new CoinLabel(computerMode.getArray()[i] + ""));
        }
        replay.setOnAction(e->{
         ComputerMode computerMode1 = new ComputerMode(computerMode.getArray(),fp);
        });
        main.setOnAction(e->Main.getStartPane().setMainMenu());
        initialize();
    }
}