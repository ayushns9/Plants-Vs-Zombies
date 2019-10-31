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

public class Pea extends Character {

    private int id ;

    Pea(Pane pane, int x, int y) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/pea.png"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x+30);
        imageView.setTranslateY(y-5);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setPreserveRatio(true);
        pane.getChildren().get(this.id).setTranslateX(pane.getChildren().get(this.id).getTranslateX());
        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.seconds(3));
        t.setNode(pane.getChildren().get(this.id));
        t.setByX(560);
        t.setCycleCount(50);
        t.setAutoReverse(false);
        t.play();

    }
}
