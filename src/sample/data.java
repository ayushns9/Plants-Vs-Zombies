package sample;

import javax.crypto.SecretKey;
import java.io.Serializable;
import java.util.ArrayList;

public class data implements Serializable {
    private ArrayList lms;
    private ArrayList allPlants=Plants.allPlants;
    private ArrayList allZombies=Zombies.allZombies;
    private int level;

    public ArrayList getAllPlants() {
        return allPlants;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList getAllZombies() {
        return allZombies;
    }

    public ArrayList getLms() {
        return lms;
    }
    data(ArrayList lms,int level){
        this.lms=lms;
        this.level= level;
    }

}
