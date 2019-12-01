package sample;

import javafx.scene.layout.Pane;

import java.io.FileNotFoundException;

public class factory {

    public static Zombies getins(Zombies o) throws FileNotFoundException {
        return new Zombies(0,0,new Pane());
    }

    public static Pea getins(Pea o) throws FileNotFoundException {
        return new Pea(new Pane(),0,0);
    }

    public static LawnMover getins(LawnMover o) throws FileNotFoundException {
        return  new LawnMover(new Pane(),0,0);
    }
}
