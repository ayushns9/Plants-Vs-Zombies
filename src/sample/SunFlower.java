package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

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
        Timeline timePlay = new Timeline(new KeyFrame(Duration.millis((double)6000), e -> {
            try {
                new SunToken(Main.getRoot(),x,y);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        timePlay.setCycleCount((Timeline.INDEFINITE));
        timePlay.play();
    }
    @Override
    public int getHealth(){
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