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
import java.util.ArrayList;
import java.util.Random;


public class Zombies extends Character {

    public int health = 100;

    protected static ArrayList<Zombies> allZombies = new ArrayList<Zombies>();
    private ImageView imageView;

    private int x = 600;
    private int y, id ;

    private Random rand = new Random();
    private int possibleLocations[] = {115,52,10,-40,-90};
    private int z;

    Zombies(Pane pane) throws FileNotFoundException {
        allZombies.add(this);
        this.id = Main.idCreater;
        ++Main.idCreater;
        Image image = new Image(new FileInputStream("./src/images/zom.gif"));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        z = rand.nextInt(5);
        imageView.setTranslateY(possibleLocations[z]);
        this.y = possibleLocations[z];
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);

        Timeline move;
        move = new Timeline(new KeyFrame(Duration.millis((double)150), e -> onestep()));
        move.setCycleCount((Timeline.INDEFINITE));
        move.play();

    }
    static void spawn() throws FileNotFoundException{
        new Zombies(Main.getRoot());
    }


    public int getHealth() {
        return health;
    }

    public void onestep(){
//        System.out.println(x);
        imageView.setTranslateX(x-2);
        x-=2;
        if(this.health<=0){
            Pane newPane = Main.getRoot();
            newPane.getChildren().remove(imageView);
            Main.setRoot(newPane);
        }
        if(this.x<=132 && Controller.getLms().get(this.z).getActive()){
            Pane newPane = Main.getRoot();
            newPane.getChildren().remove(imageView);
            Main.setRoot(newPane);
            Controller.getLms().get(this.z).move();
        }
        for(Plants p: Plants.allPlants){
            if(Math.abs(this.x-p.x)<10 && Math.abs(this.y-p.y)<10 && p.getHealth()>0){
                p.damage(10);
            }
        }
    }

    public static ArrayList<Zombies> getAllZombies() {
        return allZombies;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void damage(int val){
        this.health -= val;
    }
}
