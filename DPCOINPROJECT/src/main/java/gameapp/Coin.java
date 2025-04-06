package gameapp;


import javafx.scene.layout.StackPane;

public class Coin extends StackPane {
    private final CoinLabel coinLabel;
    private String value;
    public Coin(String value){
        coinLabel = new CoinLabel(value);
        this.getChildren().add(coinLabel);
        this.value=value;
        initialize();
    }

    public void initialize(){
        coinLabel.setDisable(true);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getValue() {
        return Integer.parseInt(value);
    }

    public CoinLabel getCoinLabel() {
        return coinLabel;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;  // Check if the same object
        if (obj == null || getClass() != obj.getClass()) return false;  // Ensure obj is of Coin class
        Coin other = (Coin) obj;
        return this.getValue() == other.getValue();  // Compare integer values
    }

}
