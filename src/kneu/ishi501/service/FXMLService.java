package kneu.ishi501.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class FXMLService {
    public static FXMLService fxmlService = new FXMLService();

    public void goToAnotherWindow(String url, String tittle){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(url));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(tittle);
        stage.getIcons().add(new Image("/kneu/ishi501/style/image1.png"));
        stage.show();
    }
}
