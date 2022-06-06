module ru.gb.javafxapplesson4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.gb.javafxapplesson4 to javafx.fxml;
    exports ru.gb.javafxapplesson4;
}