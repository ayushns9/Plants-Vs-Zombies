package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class timer {

    private Timeline timePlay;
    private int tmp = 0;
    private String S;

    Label timerLabel = new Label("00");

    public timer(){

        timerLabel.setFont(new Font("Arial",30));
        timerLabel.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        timerLabel.setTranslateY((double)68);

        Pane newRoot = Main.getRoot();
        newRoot.getChildren().add(timerLabel);
        Main.setRoot(newRoot);

        timePlay = new Timeline(new KeyFrame(Duration.millis((double)1000), e -> incrementTimer()));
        timePlay.setCycleCount((Timeline.INDEFINITE));
        timePlay.play();
    }

    private void incrementTimer() {
        if(tmp < 10000000){
            tmp++;
        }
        S = Integer.toString(tmp);
        timerLabel.setText(S);
    }
}
