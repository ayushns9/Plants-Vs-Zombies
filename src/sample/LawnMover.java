package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LawnMover extends Character {
    private boolean active = true;
    private int id,x,y;
    ImageView imageView;

    LawnMover(Pane pane, int x, int y) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/lm.png"));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        this.x = x;
        this.y = y;
        imageView.setTranslateX(x+30);
        x+=30;
        imageView.setTranslateY(y-5);
        y-=5;
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);

    }
    public boolean getActive(){
        return active;
    }

    void move(){
        this.active = false;
        Timeline move;
        move = new Timeline(new KeyFrame(Duration.millis((double)50), e -> {
            imageView.setTranslateX(x + 5);
            x += 5;
            for (Zombies z : Zombies.getAllZombies()) {
                if (Math.abs(z.getX() - this.x) < 10 && Math.abs(z.getY() - this.y) < 10 && z.getHealth()>0) {
                    System.out.println("collision");
                    z.damage(100);
                }
            }
        }));
        move.setCycleCount((Timeline.INDEFINITE));
        move.play();
    }
}
