package sample;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import sample.Main;

//import java.time.Duration;
import javafx.util.Duration;

public class timer extends Node {
    private Timeline animation;
    private int tmp = 60;
    private String S = "";

    Label label = new Label("60");

    public timer(){
        label.setTranslateX(260);
        label.setTranslateY(400);
        Pane newRoot = Main.getRoot();
        newRoot.getChildren().add(label);
        Main.setRoot(newRoot);

        animation = new Timeline(new KeyFrame(Duration.seconds(1), e -> timelabel()));
        animation.setCycleCount((Timeline.INDEFINITE));
        animation.play();
    }

    private void timelabel() {
        if(tmp > 0){
            tmp--;
        }
        S = tmp + "";
        label.setText(S);
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }
}
