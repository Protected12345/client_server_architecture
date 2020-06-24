package kneu.ishi501.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import kneu.ishi501.service.DbService;
import kneu.ishi501.service.FXMLService;
import org.bson.Document;

import java.net.URL;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    private TextField patronymic;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Button search;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search.setOnAction(actionEvent -> {
            search.getScene().getWindow().hide();
            Document document = new DbService()
                    .checkCorrectInput(
                            lastName.getText().trim().toUpperCase(),
                            firstName.getText().trim().toUpperCase(),
                            patronymic.getText().trim().toUpperCase()
                    );
            if(document == null) {
                FXMLService.fxmlService.goToAnotherWindow(
                        "/kneu/ishi501/fxml/no_client_found_window.fxml", "Клієнта не знайдено"
                );
            }
            else {
                new FXMLService().goToAnotherWindow("/kneu/ishi501/fxml/client_window.fxml",
                           "Клієнт: " +
                                lastName.getText().trim().toUpperCase() + " " +
                                firstName.getText().trim().toUpperCase() + " " +
                                patronymic.getText().trim().toUpperCase()
                );
            }
        });

    }
}
