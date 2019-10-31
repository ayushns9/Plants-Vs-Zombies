package sample;

import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SunToken extends Character {
    private int x,y, id;
    private boolean collected = false;
    SunToken(Pane pane, int x, int y) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/sun.png"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setFitHeight(35);
        imageView.setFitWidth(35);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);


        imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Pane newPane = Main.getRoot();
                newPane.getChildren().remove(imageView);
                Main.setRoot(newPane);
                collected = true;
            }
        });
    }
}
