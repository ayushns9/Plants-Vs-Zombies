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

    private int health=100;
    private int x,y;
    private transient ImageView imageView;
    transient Timeline timePlay;

    SunFlower(Pane pane,int x,int y) throws FileNotFoundException{
        super(pane);
        this.x = x;
        this.y = y;
        Plants.allPlants.add(this);
        this.place(this.x,this.y,pane);
    }
    void place(int x,int y,Pane pane) throws FileNotFoundException {
        this.imageLoc="./src/images/Sunflower.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(60);
        imageView.setFitWidth(60);
        timePlay = new Timeline(new KeyFrame(Duration.millis((double)6000), e -> {
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
        return health;
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