package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GroundNut extends Plants {

    ImageView imageView;
    int x,y,health = 500;

    GroundNut(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        Plants.allPlants.add(this);
        this.x = x;
        this.y = y;
        this.imageLoc="./src/images/wallnut.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void damage(int val){
        this.health -= val;
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void removePlant() {
        Pane newPane = Main.getRoot();
        newPane.getChildren().remove(imageView);
        Main.setRoot(newPane);
    }
}
