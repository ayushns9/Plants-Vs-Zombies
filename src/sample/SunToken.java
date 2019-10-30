package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SunToken {
    private int x,y, id;
    SunToken(Pane pane, int x, int y) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/pvzb.jpeg"));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);

    }
}
