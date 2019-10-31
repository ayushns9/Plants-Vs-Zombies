package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Controller{
    public boolean peaShooter = false;

    public void gameExit(ActionEvent event)throws IOException{
        System.exit(0);
    }


    public void newGameButtonPushed(ActionEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("GameScreen.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
        Zombies z = new Zombies(Main.getRoot());
        SunFlower s = new SunFlower(Main.getRoot(),150,0);
        SunToken k = new SunToken(Main.getRoot(),190,0);
        LawnMover l = new LawnMover(Main.getRoot(),50,100);

    }

    public void dragPeaShooter(MouseEvent event) {
        System.out.println("onDragDetected");
        peaShooter = true;
    }
    public void drop(MouseEvent event) throws FileNotFoundException {
        System.out.println("Drop");
        double x = event.getX(), y = event.getY();
        System.out.println(x);
        System.out.println(y);
        if(y<39 || x>518 || x<130){
            return;
        }
        if(y<=94){
            y = (39+94)/2;
        }
        else if(y<=144){
            y = (94+144)/2;
        }
        else if(y<=195){
            y = (144 + 195)/2;
        }
        else if(y<=246){
            y = (195 + 246)/2;
        }
        else if(y<=294){
            y = (294 + 246)/2;
        }
        else {
            return;
        }
        if(peaShooter) {
            PeaShooter p = new PeaShooter(Main.getRoot(), (int)x-30, (int)y- 160);
            peaShooter = false;
        }
    }
}
