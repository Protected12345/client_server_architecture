package kneu.ishi501;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import kneu.ishi501.service.FXMLService;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("fxml/main_window.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setTitle("Клієнська база");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("/kneu/ishi501/style/image1.png"));
        primaryStage.show();
        //FXMLService.fxmlService.goToAnotherWindow("/fxml/main_window.fxml", "Клієнська база");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
