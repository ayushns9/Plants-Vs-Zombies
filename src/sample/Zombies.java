package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;


public class Zombies {

    private final int x = 600;
    private int y;
    private Random rand = new Random();
    private int possibleLocations[] = {115,50,10,-40,-90};
    Zombies(Pane pane) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("./src/images/zom.gif"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        int y = rand.nextInt(5);
        System.out.println(y);
        imageView.setTranslateY(possibleLocations[y]);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);
    }

    void move(Pane pane){
        pane.getChildren().get(1).setTranslateX(pane.getChildren().get(1).getTranslateX()-70);
    }
}
