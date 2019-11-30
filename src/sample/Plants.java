package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public abstract class Plants extends Character implements Cloneable {
    protected static ArrayList<Plants>  allPlants = new ArrayList<Plants>();
    protected static int idCreater = 1;
    protected int  id ;
    protected String imageLoc=null;
    protected Random rand = new Random();
    private int possibleLocations[] = {115,50,10,-40,-90};
    Plants(Pane pane) throws FileNotFoundException {
        this.id = Main.idCreater;
        ++Main.idCreater;
    }
    public abstract int getHealth();
    public abstract void damage(int val);
    public abstract int getX();
    public abstract int getY();
    public abstract void removePlant();

    @Override
    public Plants clone() throws CloneNotSupportedException {
        return (Plants) super.clone();
    }
}
