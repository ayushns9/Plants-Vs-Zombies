package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


public class Controller{
    private boolean peaShooter = false,sunFlower = false, groundnut = false;
    private int peaShooterCost=50,sunFlowerCost=50,groundnutCost=50;
    private int peashooterWait=-1,sunflowerWait=-1,groundnutWait=-1;
    private static ArrayList<LawnMover> lms = new ArrayList<LawnMover>();
    private FXMLLoader curr_load = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
    static int curr_money;
    static Text border;
    private static timer tim;

    public void gameExit(ActionEvent event)throws IOException{
        System.exit(0);
    }

    public void newGameButtonPushed(ActionEvent event) throws IOException{
        Main.setRoot(curr_load.load());
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

        lms.add(new LawnMover(Main.getRoot(),55,112));
        lms.add(new LawnMover(Main.getRoot(),55,60));
        lms.add(new LawnMover(Main.getRoot(),55,12));
        lms.add(new LawnMover(Main.getRoot(),55,-42));
        lms.add(new LawnMover(Main.getRoot(),55,-92));

        tim = new timer();

        Timeline timePlay = new Timeline(new KeyFrame(Duration.millis((double)800), e -> {
            try {
                Map<String, Object> fxmlNamespace = curr_load.getNamespace();
                border = (Text) fxmlNamespace.get("money");
                Controller.curr_money = Integer.parseInt(border.getText());
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

    public static ArrayList<LawnMover> getLms() {
        return lms;
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
    public static void update_money(){
        Controller.curr_money+=20;
        Controller.border.setText(Integer.toString(curr_money));
    }

    public void dragPeaShooter(MouseEvent event) {
        System.out.println("peashooter clicked");
        peaShooter = true;
        sunFlower = false;
        groundnut = false;
    }
    public void dragSunFlower(MouseEvent event) {
        sunFlower = true;
        peaShooter =false;
        groundnut = false;
        System.out.println("sunflower clicked");

    }
    public void dragGroundNut(MouseEvent event) {
        groundnut = true;
        sunFlower = false;
        peaShooter =false;
        System.out.println("Groundnut clicked");

    }
    public void drop(MouseEvent event) throws FileNotFoundException {
        System.out.println("Drop");
        System.out.println(event.getX());
        double x = event.getX(), y = event.getY();
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
        if(peaShooter && curr_money>=peaShooterCost && Integer.parseInt(tim.getS())-peashooterWait>=5) {
            PeaShooter p = new PeaShooter(Main.getRoot(), (int)x-30, (int)y- 160);
            peaShooter = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-peaShooterCost));
            fxmlNamespace.put("money",border);
            peashooterWait = Integer.parseInt(tim.getS());
        }
        if(sunFlower && curr_money>=sunFlowerCost) {
            SunFlower p = new SunFlower(Main.getRoot(), (int)x-30, (int)y- 160);
            sunFlower = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-sunFlowerCost));
            fxmlNamespace.put("money",border);
        }
        if(groundnut && curr_money>=groundnutCost) {
            GroundNut p = new GroundNut(Main.getRoot(), (int)x-30, (int)y- 160);
            groundnut = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-groundnutCost));
            fxmlNamespace.put("money",border);
        }
    }
}
