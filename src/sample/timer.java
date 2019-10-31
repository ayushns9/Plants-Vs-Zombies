package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import javafx.util.Duration;

public class timer {
    public Timeline animation;
    public int tmp = 0;
    public String S = "";

    Label label = new Label("00");

    public timer(){
        label.setPrefWidth(40);
        label.setTranslateX(600);
        label.setTranslateY(-150);
        System.out.println(label.getTranslateX());

        Pane newRoot = Main.getRoot();
        newRoot.getChildren().add(label);
        Main.setRoot(newRoot);

        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
        animation.setCycleCount((Timeline.INDEFINITE));
        animation.play();
    }

    private void timelabel() {
        if(tmp < 1000){
            tmp++;
        }
        S = tmp + "";
        label.setText(S);
    }
}
