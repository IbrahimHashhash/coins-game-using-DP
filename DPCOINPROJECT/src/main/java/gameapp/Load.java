package gameapp;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Load {
    static Alert alert = new Alert(Alert.AlertType.ERROR);
    public static void loadGame() {
        handleLoad();
    }
    public static void handleLoad(){
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            readFromFile(filePath);
        } else {
            System.out.println("no file selected"); // if file not found then you print an error statement
        }
    }


    public static void readFromFile(String filePath) {
        FlowPane coinLabels = CustomLayOuts.fBox();
        try {
            Scanner sc = new Scanner(new File(filePath)); // takes the file path from the picked file
            String line = sc.nextLine(); // read the first line
            String[] info = line.split("[^a-zA-Z0-9]+");
            int numberOfCoins = Integer.parseInt(info[0]);
            if(numberOfCoins%2 != 0){
                throw new OddNumberException("Number of coins is odd!");
            }
            int[] arr = new int[numberOfCoins];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = Integer.parseInt(info[i + 1]); // fill array with coin values
                coinLabels.getChildren().add(new CoinLabel(info[i])); // add each value as a CoinLabel
            }
            ready(coinLabels, arr);
        } catch (FileNotFoundException ex) {
            System.out.println(ex + " ");
        }catch (ArrayIndexOutOfBoundsException ex){
            alert.setContentText("File has wrong number of coins");
            alert.show();
        }
        catch (NumberFormatException ex){
            alert.setContentText("File has non-numerical characters");
            alert.show();
        }catch (OddNumberException ex){
            alert.setContentText("Number of coins is Odd!");
            alert.show();
        }
        catch (Exception ex){
            alert.setContentText(ex.toString());
            alert.show();
            System.out.println(ex + " ");
        }
    }
    public static void ready(FlowPane coinLabel,int[] arr){
        Button bt1 = new Button("2 Players");
        Button bt2 = new Button("Vs Bot");
        VBox vBox = CustomLayOuts.vBox();
        vBox.getChildren().addAll(bt1,bt2,Main.getStartPane().getBack());

        bt1.setOnAction(e-> {
                PlayerSetup playerSetup = new PlayerSetup(coinLabel);
        });
        bt2.setOnAction(e->{
            ComputerMode computerMode = new ComputerMode(arr,coinLabel);
        });
        Main.getStartPane().setCenter(vBox);
    }
}
class OddNumberException extends Exception {
    public OddNumberException(String message) {
        super(message);  // Pass the message to the parent Exception class
    }
}
