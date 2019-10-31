package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public class Pea extends Character {

    private final int x = 600;
    private int y, id ;
    private Random rand = new Random();
    private int possibleLocations[] = {115,50,10,-40,-90};
    Pea(Pane pane) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/pea.png"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(0);
        int y = rand.nextInt(5);
        imageView.setTranslateY(0);
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setPreserveRatio(true);
        pane.getChildren().get(this.id).setTranslateX(pane.getChildren().get(this.id).getTranslateX());
        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.millis(60000));
        t.setNode(pane.getChildren().get(this.id));
        t.setByX(46000);
        t.setCycleCount(50);
        t.setAutoReverse(false);
        t.play();
    }
}
