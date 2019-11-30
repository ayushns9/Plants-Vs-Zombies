package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PeaShooter extends Plants {
    private int health=100;
    private int x,y;
    PeaShooter(Pane pane,int x,int y) throws FileNotFoundException {
        super(pane);
        super.allPlants.add(this);
        this.x = x;
        this.y = y;
        this.imageLoc="./src/images/Peashooter.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        ImageView imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(45);
        imageView.setFitWidth(45);

        Timeline move;
        move = new Timeline(new KeyFrame(Duration.millis((double)1000), e -> {
            try {
                shootpea();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));

        move.setCycleCount((Timeline.INDEFINITE));
        move.play();

    }

    public void shootpea() throws FileNotFoundException {
        Pea k = new Pea(Main.getRoot(),this.x,this.y);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void damage(int val){
        this.health-=val;
    }
}
