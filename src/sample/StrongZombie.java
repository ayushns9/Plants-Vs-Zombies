package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class StrongZombie extends Character {

    public int health = 200;

    private transient ImageView imageView;
    public static ArrayList<StrongZombie> allSZombies = new ArrayList<StrongZombie>();

    private int x = 600;
    private int y, id ;

    private Random rand = new Random();
    private int possibleLocations[] = {115,52,10,-40,-90};
    private int z;

    transient Timeline move;

    StrongZombie(Pane pane) throws FileNotFoundException {
        allSZombies.add(this);
        this.id = Main.idCreater;
        ++Main.idCreater;
        z = rand.nextInt(5);
        this.place(z,x,pane);

    }
    void place(int z,int x,Pane pane) throws FileNotFoundException {
        Image image = new Image(new FileInputStream("./src/images/Conehead_Zombie.gif"));
        imageView = new ImageView(image);
        pane.getChildren().add(imageView);
        imageView.setTranslateX(x);
        imageView.setTranslateY(possibleLocations[z]);
        this.y = possibleLocations[z];
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setPreserveRatio(true);


        move = new Timeline(new KeyFrame(Duration.millis((double)150), e -> {
            try {
                onestep();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch(GameLostException g){
                try {
                    Controller.lostGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            catch(GameWonException gg){
                try {
                    Controller.wonGame();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));
        move.setCycleCount((Timeline.INDEFINITE));
        move.play();
    }
    static void spawn() throws FileNotFoundException{
        new StrongZombie(Main.getRoot());
    }


    public int getHealth() {
        return health;
    }

    public void onestep() throws InterruptedException, GameLostException, GameWonException {
        imageView.setTranslateX(x - 2);
        x -= 2;
        if (this.health <= 0) {
            Pane newPane = Main.getRoot();
            newPane.getChildren().remove(imageView);
            Main.setRoot(newPane);
//            allZombies.remove(this);
            int allDead = 0;
            for(Zombies zo: Zombies.allZombies){
                if(zo.health<=0)
                    allDead++;
            }
            for(StrongZombie zo: StrongZombie.getAllZombies()){
                if(zo.health<=0)
                    allDead++;
            }

            if(allDead==Controller.getLevel()){
                throw new GameWonException("You won");
            }
        }
        if (this.x <= 132 && Controller.getLms().get(this.z).getActive() && this.health>0) {
            Pane newPane = Main.getRoot();
            newPane.getChildren().remove(imageView);
            Main.setRoot(newPane);
            Controller.getLms().get(this.z).move();
            this.health -= 100;
        } else if (this.x <= 132 && this.health>0) {
            this.health=0;
            throw new GameLostException("You lost");
        }
        for (Plants p : Plants.allPlants) {

            if (this.health > 0 && Math.abs(this.x - p.getX()) < 10 && Math.abs(this.y - p.getY()) < 10 && p.getHealth() > 0) {
                try{
                    p = ((Cherry) p);
                    p.removePlant();
                    p.damage(100);
                    this.health=0;
                }
                catch (Exception e){

                }
                System.out.println("collide");
                if (Integer.parseInt(Controller.getTim().getS()) % 2 == 1 && p.getHealth() > 0 && this.health > 0) {
                    p.damage(10);
                    System.out.println(p.getHealth());
                }
                imageView.setTranslateX(x + 2);
                x += 2;
            }

            if (p.getHealth() <= 0) {
                p.removePlant();
            }
        }
    }

    public static ArrayList<StrongZombie> getAllZombies() {
        return allSZombies;
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
