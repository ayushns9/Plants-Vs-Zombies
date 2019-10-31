package sample;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Zombies {

    private final int x = 600;
    private int y, id ;

    private Random rand = new Random();
    private int possibleLocations[] = {115,50,10,-40,-90};

    Zombies(Pane pane) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/zom.gif"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        int y = rand.nextInt(5);
        imageView.setTranslateY(possibleLocations[y]);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);
//        pane.getChildren().get(this.id).setTranslateX(pane.getChildren().get(this.id).getTranslateX()-70);
        TranslateTransition t = new TranslateTransition();
        t.setDuration(Duration.millis(20000));
        t.setNode(imageView);
        t.setByX(-400);
        t.setCycleCount(50);
        t.setAutoReverse(false);
        t.play();
    }

}
