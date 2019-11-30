package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class PeaShooter extends Plants {
    private static Map<Pair,PeaShooter> instances = new HashMap<Pair,PeaShooter>();
    private int health=100;
    private int x,y;
    private ImageView imageView;
    Timeline move;
    PeaShooter(Pane pane,int x,int y) throws FileNotFoundException {
        super(pane);
        super.allPlants.add(this);
        this.x = x;
        this.y = y;
        this.imageLoc="./src/images/Peashooter.gif";
        Image image = new Image(new FileInputStream(imageLoc));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitHeight(45);
        imageView.setFitWidth(45);

        move = new Timeline(new KeyFrame(Duration.millis((double)2500), e -> {
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
        Pea k;
        if(this.health>0)
            k = new Pea(Main.getRoot(),this.x,this.y);
    }

    public PeaShooter getInstance(int x, int y) throws FileNotFoundException {
        Pair pp = new Pair(x,y);
        if(!instances.containsKey(pp)){
            instances.put(pp, new PeaShooter(new Pane(),x,y));
        }
        return instances.get(pp);
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void damage(int val){
        this.health-=val;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void removePlant() {
        Pane newPane = Main.getRoot();
        newPane.getChildren().remove(imageView);
        Main.setRoot(newPane);
    }
}
