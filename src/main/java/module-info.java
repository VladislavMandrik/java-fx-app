module ru.gb.javafxapplesson4 {
    requires javafx.controls;
    requires javafx.fxml;

    exports ru.gb.javafxapplesson4.client;
    opens ru.gb.javafxapplesson4.client to javafx.fxml;
}
