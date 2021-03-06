package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;


public class Controller{
    private boolean peaShooter = false,sunFlower = false, groundnut = false,cherry=false;
    private int peaShooterCost=100,sunFlowerCost=50,groundnutCost=50,cherryCost=100;
    private int peashooterWait=-10,sunflowerWait=-10,groundnutWait=-10,cherryWait=-10;
    private static ArrayList<LawnMover> lms = new ArrayList<LawnMover>();
    private static ArrayList<String> saves = new ArrayList<>();
    private static String user;
    static FXMLLoader curr_load = new FXMLLoader(Controller.class.getResource("GameScreen.fxml"));
    private FXMLLoader info = new FXMLLoader(getClass().getResource("PlayerInfo.fxml"));
//    static FXMLLoader curr_load = new FXMLLoader(Controller.class.getResource("GameScreen.fxml"));
    static int curr_money=400;
    static Text border;
    private static timer tim;
    private static Timeline timePlay;
    private static Timeline sun_falling;
    private static int level = 1;
    private static Map<String, Object> fxmltext;
    public void gameExit(ActionEvent event)throws IOException{
        System.exit(0);
    }

    public static int getLevel() {
        return level;
    }
    public void savegame(ActionEvent event) throws IOException, ClassNotFoundException {
        save.saved(new data(lms,level),user);
        saves.add(user);
        System.out.println(saves);
        FXMLLoader cu = new FXMLLoader(getClass().getResource("MainScreen.fxml"));
        Main.setRoot(cu.load());
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
        System.out.println("sss");
    }
    public void paused(ActionEvent event) throws IOException{
        save.saved(new data(lms,level),user);
    }


    public void newGameButtonPushed(ActionEvent event) throws IOException{
        curr_load = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
        Main.setRoot(curr_load.load());
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();

        lms = new ArrayList<LawnMover>();
        Plants.allPlants = new ArrayList<Plants>();
        Zombies.allZombies = new ArrayList<Zombies>();

        lms.add(new LawnMover(Main.getRoot(),55,112));
        lms.add(new LawnMover(Main.getRoot(),55,60));
        lms.add(new LawnMover(Main.getRoot(),55,12));
        lms.add(new LawnMover(Main.getRoot(),55,-42));
        lms.add(new LawnMover(Main.getRoot(),55,-92));

        tim = new timer();

        Map<String, Object> fxmlNamespace = curr_load.getNamespace();
        border = (Text) fxmlNamespace.get("money");
        Controller.curr_money = Integer.parseInt(border.getText());

        timePlay = new Timeline(new KeyFrame(Duration.millis((double)3000), e -> {
            try {
                border = (Text) fxmlNamespace.get("money");
                Controller.curr_money = Integer.parseInt(border.getText());
                Random rand = new Random();
                int xx = rand.nextInt(2);
                if(xx==0)
                    StrongZombie.spawn(rand.nextInt(5), 600);
                else
                    Zombies.spawn(rand.nextInt(5),600);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        if(level==-1)
            timePlay.setCycleCount(Timeline.INDEFINITE);
        else
            timePlay.setCycleCount((2*level));
        timePlay.play();
        Random rand = new Random();
        int possibleLocations[] = {140,200,500,300,400,250,350,450,215,315,180};
        sun_falling = new Timeline(new KeyFrame(Duration.millis((double)10000), e -> {
            try {
                (new SunToken(Main.getRoot(),possibleLocations[rand.nextInt(possibleLocations.length)],-140)).onestep();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        sun_falling.setCycleCount((Timeline.INDEFINITE));
        sun_falling.play();
        System.out.println(user);
    }

    public void startLevel1(ActionEvent event) throws IOException {
        this.level = 1;
        Enterinfo(event);
//        newGameButtonPushed(event);
    }public void startLevel2(ActionEvent event) throws IOException {
        this.level = 2;
        Enterinfo(event);
//        newGameButtonPushed(event);
    }public void startLevel3(ActionEvent event) throws IOException {
        this.level = 3;
        Enterinfo(event);
//        newGameButtonPushed(event);
    }public void startLevel4(ActionEvent event) throws IOException {
        this.level = 4;
        Enterinfo(event);

//        newGameButtonPushed(event);
    }public void startLevel5(ActionEvent event) throws IOException {
        this.level = 5;
        Enterinfo(event);

//        newGameButtonPushed(event);
    }
    public void startLevel6(ActionEvent event) throws IOException {
        this.level = -1;
        newGameButtonPushed(event);
    }

    public static timer getTim() {
        return tim;
    }

    public void Enterinfo(ActionEvent event) throws IOException {
//        timePlay.stop();
//        sun_falling.stop();
        for(Zombies z: Zombies.allZombies){
            z.move.stop();
        }
        for(StrongZombie z: StrongZombie.allSZombies){
            z.move.stop();
        }
        for(Plants p: Plants.allPlants){
            try {
                ((SunFlower) p).timePlay.stop();
            } catch (Exception e) {
                try {
                    ((PeaShooter) p).move.stop();
                }
                catch(Exception ee){
                }
            }
        }
        Main.setRoot(FXMLLoader.load(getClass().getResource("PlayerInfo.fxml"))) ;
        info=new FXMLLoader(getClass().getResource("PlayerInfo.fxml"));
        Main.setRoot(info.load()) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
        fxmltext = info.getNamespace();
        System.out.println(fxmltext);

    }

    public void savename(ActionEvent event) throws IOException {
        System.out.println(fxmltext);
        TextField tf = (TextField) fxmltext.get("user1");
        System.out.println(tf);
        user = tf.getText();
        System.out.println(user);
        newGameButtonPushed(event);
    }
    public void rest(ActionEvent event) throws IOException,ClassNotFoundException{
        save.saved(new data(lms,level),user);
        data d = save.unsave(user);
        FXMLLoader curr_loa = new FXMLLoader(getClass().getResource("GameScreen.fxml"));

        Main.setRoot(curr_loa.load());

        Scene scene1 = new Scene(Main.getRoot());
        System.out.println(Main.window);
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene1);
        Main.window.show();

        lms = d.getLms();
        Plants.allPlants = d.getAllPlants();
        Zombies.allZombies = d.getAllZombies();
        StrongZombie.allSZombies=d.getConeZom();

        for (int j = 0; j <lms.size(); j++) {
            try {
                if(lms.get(j).getActive())
                    new LawnMover(Main.getRoot(),lms.get(j).getX(),lms.get(j).getY());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
//
        tim = new timer();
        for(Plants p: Plants.allPlants){
            try {
                p=(PeaShooter) p;
                ((PeaShooter) p).place(p.getX(),p.getY(),Main.getRoot());
            } catch (Exception ex) {

            }

            try {
                p=(SunFlower) p;
                ((SunFlower) p).place(p.getX(),p.getY(),Main.getRoot());
            } catch (Exception ex) {
//                        ex.printStackTrace();
            }
            try {
                p=(GroundNut) p;
                ((GroundNut) p).place(p.getX(),p.getY(),Main.getRoot());
            } catch (Exception ex) {
//                        ex.printStackTrace();
            }
            try {
                p=(Cherry) p;
                ((Cherry) p).place(p.getX(),p.getY(),Main.getRoot());
            } catch (Exception ex) {
//                        ex.printStackTrace();
            }

        }
        Map<String, Object> fxmNamespace = curr_load.getNamespace();
        border = (Text) fxmNamespace.get("money");
        border.setText(String.valueOf(d.getMoney()));
        Controller.curr_money = Integer.parseInt(border.getText());

        for(Zombies z: Zombies.allZombies){
            try {
                z.place(z.getZ(),z.getX(),Main.getRoot());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        for(StrongZombie z: StrongZombie.getAllZombies()){
            try {
                z.place(z.getZ(),z.getX(),Main.getRoot());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }

        timePlay = new Timeline(new KeyFrame(Duration.millis((double)3000), q -> {
            try {
                border = (Text) fxmNamespace.get("money");
                Controller.curr_money = Integer.parseInt(border.getText());
                Random rand = new Random();
                int xx = rand.nextInt(2);
                if(xx==0)
                    StrongZombie.spawn(rand.nextInt(5),600);
                else
                    Zombies.spawn(rand.nextInt(5),600);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        timePlay.setCycleCount((2*level));
        timePlay.play();
        Random rand = new Random();
        int possibleLocations[] = {140,200,500,300,400,250,350,450,215,315,180};
        sun_falling = new Timeline(new KeyFrame(Duration.millis((double)10000), t -> {
            try {
                (new SunToken(Main.getRoot(),possibleLocations[rand.nextInt(possibleLocations.length)],-140)).onestep();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }));
        sun_falling.setCycleCount((Timeline.INDEFINITE));
        sun_falling.play();
        System.out.println(user);



    }

    public static void lostGame() throws IOException {

        sun_falling.stop();
        timePlay.stop();
        for(Zombies z: Zombies.allZombies){
            z.move.stop();
        }
        for(Plants p: Plants.allPlants){
            try {
                ((SunFlower) p).timePlay.stop();
                System.out.println("1");
            } catch (Exception e) {
                try {
                    ((PeaShooter) p).move.stop();
                }
                catch(Exception ee){
                    //
                }
            }
        }
        Main.setRoot(FXMLLoader.load(Controller.class.getResource("lost_page.fxml")));
        Scene scene = new Scene(Main.getRoot());
        Main.window.setScene(scene);
        Main.window.show();

    }
    public static void wonGame() throws IOException {
        timePlay.stop();
        sun_falling.stop();
        for(Zombies z: Zombies.allZombies){
            z.move.stop();
        }
        for(StrongZombie z: StrongZombie.allSZombies){
            z.move.stop();
        }
        for(Plants p: Plants.allPlants){
            try {
                ((SunFlower) p).timePlay.stop();
            } catch (Exception e) {
                try {
                    ((PeaShooter) p).move.stop();
                }
                catch(Exception ee){
                }
            }
        }
        Main.setRoot(FXMLLoader.load(Controller.class.getResource("win_page.fxml")));
        Scene scene = new Scene(Main.getRoot());
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
        timePlay.stop();
        sun_falling.stop();
        for(Zombies z: Zombies.allZombies){
            z.move.stop();
        }
        for(StrongZombie z: StrongZombie.allSZombies){
            z.move.stop();
        }
        for(Plants p: Plants.allPlants){
            try {
                ((SunFlower) p).timePlay.stop();
            } catch (Exception e) {
                try {
                    ((PeaShooter) p).move.stop();
                }
                catch(Exception ee){
                }
            }
        }
        Main.setRoot(FXMLLoader.load(getClass().getResource("Pause.fxml"))) ;
        Scene scene = new Scene(Main.getRoot());
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
    }
    public void Load(ActionEvent event) throws IOException, ClassNotFoundException {

        sun_falling.stop();
        FXMLLoader resume = new FXMLLoader(getClass().getResource("Load.fxml"));
        Main.setRoot(resume.load()) ;
        Scene scene = new Scene(Main.getRoot());
        int temp=0;
        for (int i = 0; i <Math.min(4,saves.size()) ; i++) {
            Map<String, Object> fxmlNamespace = resume.getNamespace();
            System.out.println(fxmlNamespace);
            Button btn = (Button) fxmlNamespace.get("btn"+(i+1));
            System.out.println(saves);
            System.out.println(saves.get(saves.size()-i-1));
            btn.setText(saves.get(saves.size()-i-1));
            data d = save.unsave(saves.get(saves.size()-temp-1));
            btn.setOnAction(e -> {
                curr_load = new FXMLLoader(getClass().getResource("GameScreen.fxml"));
                try {
                    Main.setRoot(curr_load.load());
                    System.out.println("dd");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Scene scene1 = new Scene(Main.getRoot());
                System.out.println(Main.window);
                Main.window = (Stage) ((Node)e.getSource()).getScene().getWindow();
                Main.window.setScene(scene1);
                Main.window.show();

                lms = d.getLms();
                Plants.allPlants = d.getAllPlants();
                Zombies.allZombies = d.getAllZombies();
                StrongZombie.allSZombies=d.getConeZom();

                for (int j = 0; j <lms.size(); j++) {
                    try {
                        if(lms.get(j).getActive())
                        new LawnMover(Main.getRoot(),lms.get(j).getX(),lms.get(j).getY());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
//
                tim = new timer();
                for(Plants p: Plants.allPlants){
                    try {
                        p=(PeaShooter) p;
                        ((PeaShooter) p).place(p.getX(),p.getY(),Main.getRoot());
                    } catch (Exception ex) {

                    }

                    try {
                        p=(SunFlower) p;
                        ((SunFlower) p).place(p.getX(),p.getY(),Main.getRoot());
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }
                    try {
                        p=(GroundNut) p;
                        ((GroundNut) p).place(p.getX(),p.getY(),Main.getRoot());
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }
                    try {
                        p=(Cherry) p;
                        ((Cherry) p).place(p.getX(),p.getY(),Main.getRoot());
                    } catch (Exception ex) {
//                        ex.printStackTrace();
                    }

                }
                Map<String, Object> fxmNamespace = curr_load.getNamespace();
                border = (Text) fxmNamespace.get("money");
                border.setText(String.valueOf(d.getMoney()));
                Controller.curr_money = Integer.parseInt(border.getText());

                for(Zombies z: Zombies.allZombies){
                    try {
                        z.place(z.getZ(),z.getX(),Main.getRoot());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
                for(StrongZombie z: StrongZombie.getAllZombies()){
                    try {
                        z.place(z.getZ(),z.getX(),Main.getRoot());
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }

                timePlay = new Timeline(new KeyFrame(Duration.millis((double)3000), q -> {
                    try {
                        border = (Text) fxmNamespace.get("money");
                        Controller.curr_money = Integer.parseInt(border.getText());
                        Random rand = new Random();
                        int xx = rand.nextInt(2);
                        if(xx==0)
                            StrongZombie.spawn(rand.nextInt(5),600);
                        else
                            Zombies.spawn(rand.nextInt(5),600);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }));
                timePlay.setCycleCount((2*level));
                timePlay.play();
                Random rand = new Random();
                int possibleLocations[] = {140,200,500,300,400,250,350,450,215,315,180};
                sun_falling = new Timeline(new KeyFrame(Duration.millis((double)10000), t -> {
                    try {
                        (new SunToken(Main.getRoot(),possibleLocations[rand.nextInt(possibleLocations.length)],-140)).onestep();
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }));
                sun_falling.setCycleCount((Timeline.INDEFINITE));
                sun_falling.play();
                System.out.println(user);

                System.out.println(d);

            });

        }
        Main.window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Main.window.setScene(scene);
        Main.window.show();
    }

    public void Back(ActionEvent event) throws IOException {
        sun_falling.stop();
        for(Zombies z: Zombies.allZombies){
            z.move.stop();
        }
        for(StrongZombie z: StrongZombie.allSZombies){
            z.move.stop();
        }
        for(Plants p: Plants.allPlants){
            try {
                ((SunFlower) p).timePlay.stop();
            } catch (Exception e) {
                try {
                    ((PeaShooter) p).move.stop();
                }
                catch(Exception ee){
                }
            }
        }
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
        cherry=false;
        peaShooter = true;
        sunFlower = false;
        groundnut = false;
    }
    public void dragSunFlower(MouseEvent event) {
        if(this.level>=2 || this.level ==-1) {
            cherry = false;
            sunFlower = true;
            peaShooter = false;
            groundnut = false;
            System.out.println("sunflower clicked");
        }

    }
    public void dragGroundNut(MouseEvent event) {
        if(this.level>=3 || this.level ==-1) {
            cherry = false;
            groundnut = true;
            sunFlower = false;
            peaShooter = false;
            System.out.println("Groundnut clicked");
        }
    }
    public void dragCherry(MouseEvent event) {
        if(this.level>=4 || this.level ==-1) {
            groundnut = false;
            sunFlower = false;
            peaShooter = false;
            cherry = true;
            System.out.println("Groundnut clicked");
        }
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

        String planted = "";

        if(peaShooter && curr_money>=peaShooterCost && (Integer.parseInt(tim.getS())-peashooterWait)>=5) {
            PeaShooter p = new PeaShooter(Main.getRoot(), (int)x-30, (int)y- 160);
            peaShooter = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-peaShooterCost));
            fxmlNamespace.put("money",border);
            peashooterWait = Integer.parseInt(tim.getS());
            planted = "peashooter";
        }

        if(sunFlower && curr_money>=sunFlowerCost && (Integer.parseInt(tim.getS())-sunflowerWait)>=5) {
            SunFlower p = new SunFlower(Main.getRoot(), (int)x-30, (int)y- 160);
            sunFlower = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-sunFlowerCost));
            fxmlNamespace.put("money",border);
            sunflowerWait = Integer.parseInt(tim.getS());
            planted = "sunflower";

        }

        if(groundnut && curr_money>=groundnutCost&& (Integer.parseInt(tim.getS())-groundnutWait)>=5) {
            GroundNut p = new GroundNut(Main.getRoot(), (int)x-30, (int)y- 160);
            groundnut = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-groundnutCost));
            fxmlNamespace.put("money",border);
            groundnutWait = Integer.parseInt(tim.getS());
            planted = "groundnut";

        }

        if(cherry && curr_money>=cherryCost&& (Integer.parseInt(tim.getS())-cherryWait)>=5) {
            Cherry p = new Cherry(Main.getRoot(), (int)x-30, (int)y- 160);
            cherry = false;
            Map<String, Object> fxmlNamespace = curr_load.getNamespace();
            border.setText(String.valueOf(curr_money-cherryCost));
            fxmlNamespace.put("money",border);
            cherryWait = Integer.parseInt(tim.getS());
            planted = "cherry";

        }
        switch(planted){
            case "peashooter":
                System.out.println("Planted Pea Shooter"); break;
            case "groundnut":
                System.out.println("Planted Ground Nut");break;
            case "cherry":
                System.out.println("Planted cherry bomb");break;
            case "sunflower":
                System.out.println("Planted Sun flower");break;
        }
    }
}
