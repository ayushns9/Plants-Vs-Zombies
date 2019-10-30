package sample;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import static sample.Main.window;

public class Zombies {

    private final int x = 600;
    private int y;
    private Random rand = new Random();
    private int possibleLocations[] = {115,60,10,-40,-90};
    Zombies(Pane pane) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("./src/images/pvzb.jpeg"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        int y = rand.nextInt(5);
        System.out.println(y);
        imageView.setTranslateY(possibleLocations[y]);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setPreserveRatio(true);
    }

    void move(Pane pane){
        pane.getChildren().get(1).setTranslateX(pane.getChildren().get(1).getTranslateX()-70);
    }

}
