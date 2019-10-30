package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage window;
//    int mesh[6][10];


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
        z.move(pane);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
