package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Controller {

    public void newGameButtonPushed(ActionEvent event) throws IOException {
        Main.changeScene("GameScreen.fxml", event);
    }
}
