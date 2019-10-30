package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    public static int idCreater = 1;
    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Pane root = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene  = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void changeScene(String fxml, ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(Main.class.getResource(fxml));
        Scene scene = new Scene(pane);
        window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
        Zombies z = new Zombies(pane);
        PeaShooter p = new PeaShooter(pane,300,20);
        z.move(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
