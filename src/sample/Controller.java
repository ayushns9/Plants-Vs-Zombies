package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    public void newGameButtonPushed(ActionEvent event) throws IOException {
        Pane pane = FXMLLoader.load(getClass().getResource("GameScreen.fxml"));
        Scene scene = new Scene(pane);
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
        Zombies z = new Zombies(pane);
        PeaShooter p = new PeaShooter(pane,0,0);
        Pea k = new Pea(pane);
    }

    public void dragPeaShooter(ActionEvent event) throws IOException {
        //create peaShooter on current pointer location.

    }
}
