package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class Cherry extends Plants {
    boolean used = false;
    int x,y,health=100;
    ImageView imageView;
    Cherry(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        allPlants.add(this);
        this.x = x;
        this.y = y;
        this.imageLoc="./src/images/cherry.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);

        Timeline timePlay = new Timeline(new KeyFrame(Duration.millis((double)1000), e -> {
            if(this.health<100 && !used){
                Image i;
                try {
                    i = new Image(new FileInputStream("./src/images/bomb.gif"));
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                imageView = new ImageView(image);
                pane.getChildren().add(imageView);
                imageView.setTranslateX(x);
                imageView.setTranslateY(y);
                imageView.setFitHeight(40);
                imageView.setFitWidth(40);
                this.used = true;
            }
//            else if(used){
//                Pane newPane = Main.getRoot();
//                newPane.getChildren().remove(imageView);
//                Main.setRoot(newPane);
//            }
        }));
        timePlay.setCycleCount((Timeline.INDEFINITE));
        timePlay.play();
    }
    @Override
    public int getHealth() {
        return health;
    }
    @Override
    public void damage(int val){
        health-=val;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void removePlant() {
        Pane newPane = Main.getRoot();
        newPane.getChildren().remove(imageView);
        Main.setRoot(newPane);
    }
}
