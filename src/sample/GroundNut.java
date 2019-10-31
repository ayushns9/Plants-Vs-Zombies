package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GroundNut extends Plants {
    GroundNut(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        this.imageLoc="./src/images/wallnut.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
    }
}
