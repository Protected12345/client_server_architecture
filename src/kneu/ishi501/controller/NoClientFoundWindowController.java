package kneu.ishi501.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import kneu.ishi501.service.FXMLService;

import java.net.URL;
import java.util.ResourceBundle;

import static kneu.ishi501.service.FXMLService.fxmlService;

public class NoClientFoundWindowController implements Initializable {

    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        back.setOnAction(actionEvent -> {
            back.getScene().getWindow().hide();
            FXMLService.fxmlService.goToAnotherWindow("/kneu/ishi501/fxml/main_window.fxml", "Клієнська база");
        });
    }
}
