package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;


public class Controller{
    private boolean peaShooter = false,sunFlower = false, groundnut = false;
    private FXMLLoader curr_load = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
    static int curr_money;
    public void gameExit(ActionEvent event)throws IOException{
        System.exit(0);
    }

    public void newGameButtonPushed(ActionEvent event) throws IOException{
        Main.setRoot(curr_load.load());
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
        Zombies z = new Zombies(Main.getRoot());
        Zombies z2 = new Zombies(Main.getRoot());
        LawnMover l = new LawnMover(Main.getRoot(),55,112);
        l.move();
        LawnMover l1 = new LawnMover(Main.getRoot(),55,62);
        LawnMover l2 = new LawnMover(Main.getRoot(),55,12);
        LawnMover l3 = new LawnMover(Main.getRoot(),55,-42);
        LawnMover l4 = new LawnMover(Main.getRoot(),55,-92);

        timer tim = new timer();
        Timeline timePlay = new Timeline(new KeyFrame(Duration.millis((double)7000), e -> {
            try {
                Map<String, Object> fxmlNamespace = curr_load.getNamespace();
                Text border = (Text) fxmlNamespace.get("money");
                Controller.curr_money = Integer.parseInt(border.getText());
                System.out.println(Controller.curr_money);

                Zombies.spawn();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        timePlay.setCycleCount((Timeline.INDEFINITE));
        timePlay.play();



    }

    public void Enterinfo(ActionEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("PlayerInfo.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

    }
    public void level(ActionEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("choose.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

    }
    public void Pause(MouseEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("Pause.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

    }
    public void Load(ActionEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("Load.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

    }    public void Back(ActionEvent event) throws IOException {
        Main.setRoot(FXMLLoader.load(getClass().getResource("MainScreen.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

    }

    public void dragPeaShooter(MouseEvent event) {
        System.out.println(Controller.curr_money);
        if(Controller.curr_money>190){
            System.out.println("peashooter clicked");
            peaShooter = true;
            sunFlower = false;
            groundnut = false;
        }

    }
    public void dragSunFlower(MouseEvent event) {
        if(Controller.curr_money>190){
            sunFlower = true;
            peaShooter =false;
            groundnut = false;
        }
        System.out.println("sunflower clicked");

    }
    public void dragGroundNut(MouseEvent event) {
        if(Controller.curr_money>190){
            groundnut = true;
            sunFlower = false;
            peaShooter =false;
        }
        System.out.println("Groundnut clicked");

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
        if(sunFlower) {
            SunFlower p = new SunFlower(Main.getRoot(), (int)x-30, (int)y- 160);
            sunFlower = false;
        }
        if(groundnut) {
            GroundNut p = new GroundNut(Main.getRoot(), (int)x-30, (int)y- 160);
            groundnut = false;
        }
    }
}
