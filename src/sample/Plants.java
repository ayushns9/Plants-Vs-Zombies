package sample;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

public abstract class Plants extends Character {
    protected static int idCreater = 1;
    protected final int x = 600;
    protected int y, id ;
    protected String imageLoc=null;
    protected Random rand = new Random();
    private int possibleLocations[] = {115,50,10,-40,-90};
    Plants(Pane pane) throws FileNotFoundException {
        this.id = idCreater;
        ++idCreater;

    }

}
