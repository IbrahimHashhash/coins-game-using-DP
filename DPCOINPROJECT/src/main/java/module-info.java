module gameapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens gameapp to javafx.fxml;
    exports gameapp;
}
