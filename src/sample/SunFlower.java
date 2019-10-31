package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SunFlower extends Plants {

    SunFlower(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        this.imageLoc="./src/images/Sunflower.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        SunToken st = new SunToken(Main.getRoot(),x,y);
    }
}