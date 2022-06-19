package ru.gb.javafxapplesson4;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Controller {
    @FXML
    private TextArea historyArea;
    @FXML
    private TextField userAnswer;

    @FXML
    public void writeToChatButton() {
        String userWrite = userAnswer.getText();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        if (userWrite.isBlank()) {
            return;

        }

        String text = " " + userAnswer.getText();
        historyArea.appendText(simpleDateFormat.format(date) + text + "\n");
        userAnswer.clear();
        userAnswer.requestFocus();
    }
}