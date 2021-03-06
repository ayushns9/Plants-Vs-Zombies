package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Pea extends Character implements Cloneable{

    private int id, x, y;
    private boolean isActive = true;
    transient ImageView imageView;
    transient Timeline move;
    Pea(Pane pane, int x, int y) throws FileNotFoundException {
        this.x = x+30;
        this.y = y-5;
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/pea.png"));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x+30);
        imageView.setTranslateY(y-5);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setPreserveRatio(true);

        move = new Timeline(new KeyFrame(Duration.millis((double)30), e -> onestep()));
        move.setCycleCount((Timeline.INDEFINITE));
        move.play();
    }


    @Override
    public Pea clone() throws CloneNotSupportedException {
        return (Pea)super.clone();
    }

    public void onestep(){

        if(isActive) {
            imageView.setTranslateX(x + 5);
            x += 5;
            for (Zombies z : Zombies.allZombies) {
                if (Math.abs(z.getX() - this.x) <= 20 && Math.abs(z.getY() - this.y) <= 10  && z.getHealth()>0) {
                    System.out.println("collision");
                    System.out.println(z.getHealth());
                    Pane newPane = Main.getRoot();
                    newPane.getChildren().remove(imageView);
                    Main.setRoot(newPane);
                    this.isActive = false;
                    z.damage(25);
                }
            }
            for(StrongZombie z: StrongZombie.allSZombies){
                if (Math.abs(z.getX() - this.x) <= 20 && Math.abs(z.getY() - this.y) <= 10  && z.getHealth()>0) {
                    System.out.println("collision");
                    System.out.println(z.getHealth());
                    Pane newPane = Main.getRoot();
                    newPane.getChildren().remove(imageView);
                    Main.setRoot(newPane);
                    this.isActive = false;
                    z.damage(25);
                }
            }
        }
    }
}
