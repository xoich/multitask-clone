package xyz.parisi.unical.multitask.balancegame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.Cross;
import xyz.parisi.unical.multitask.Keyboard;
import xyz.parisi.unical.multitask.MiniGame;
import xyz.parisi.unical.multitask.Window;

public class BalanceGame extends Pane implements MiniGame, Window {
    private final SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private final SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    private final Ball ball = new Ball();
    private final Rectangle bar = new Rectangle(-50, -5, 100, 10);
    private final Pane objects = new Pane();

    public BalanceGame(double w, double h) {
        myWidth.set(w);
        myHeight.set(h);
        Rectangle bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.LIGHTCORAL.desaturate());
        bg.setStroke(Color.GRAY);

        bar.setFill(Color.BLACK);
        bar.setRotate(0.2);
        ball.setFill(Color.RED.brighter());
        objects.getChildren().addAll(bar, ball);
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));
        getChildren().addAll(bg, objects);
    }

    @Override
    public void showGameOver() {
        objects.getChildren().addAll(new Cross());
    }

    @Override
    public String getInstructionText() {
        return "Keep the ball balanced on the platform!\nUse the S and F keys.";
    }

    @Override
    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    @Override
    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        if (keyboard.isPressed(KeyCode.F))
            bar.setRotate(bar.getRotate() + 0.7);
        if (keyboard.isPressed(KeyCode.S))
            bar.setRotate(bar.getRotate() - 0.7);
        return ball.update(timeDelta, bar.getRotate());
    }
}
