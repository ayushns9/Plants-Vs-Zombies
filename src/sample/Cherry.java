package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Cherry extends Plants {
    Cherry(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        this.imageLoc="./src/images/cherry.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
    }@Override
    public int getHealth() {
        return 0;
    }
    @Override
    public void damage(int val){

    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void removePlant() {

    }
}
