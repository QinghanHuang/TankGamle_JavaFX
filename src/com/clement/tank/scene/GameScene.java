package com.clement.tank.scene;

import com.clement.tank.Director;
import com.clement.tank.sprite.Background;
import com.clement.tank.sprite.Bullet;
import com.clement.tank.sprite.Explore;
import com.clement.tank.sprite.Tank;
import com.clement.tank.util.Direction;
import com.clement.tank.util.Group;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class GameScene {
    private Canvas canvas = new Canvas(Director.WIDTH, Director.HEIGHT);
    private GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

    private KeyProcess keyProcess = new KeyProcess();

    private Refresh refresh = new Refresh();

    private boolean running = false;

    private Background background = new Background();
    //储存玩家坦克
    private Tank self = new Tank(400, 500, Group.GREEN, Direction.STOP, Direction.UP, this);
    //储存子弹list
    private List<Bullet> bullets=new ArrayList<>();
    //储存敌方坦克
    private List<Tank> enemies=new ArrayList<>();
    //储存爆炸效果
    private List<Explore> explores=new ArrayList<>();


    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Tank> getEnemies(){
        return enemies;
    }

    public List<Explore> getExplores() {
        return explores;
    }

    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
        self.impact(enemies);

        for (int i = 0; i <bullets.size() ; i++) {
            Bullet bullet=bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impact(enemies);
        }
        for (int i = 0; i <enemies.size() ; i++) {
            enemies.get(i).paint(graphicsContext);
        }
        for (int i = 0; i <explores.size() ; i++) {
            explores.get(i).paint(graphicsContext);
        }
    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(keyProcess);
        stage.getScene().setOnKeyReleased(keyProcess);
        running = true;
        //初始Sprite
        initSprite();
        refresh.start();
    }

    private void initSprite(){
        for (int i = 0; i < 6; i++) {
            Tank tank=new Tank(200+i*80,100,Group.RED,Direction.STOP,Direction.DOWN,this);
            enemies.add(tank);
        }
    }


    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        refresh.stop();
    }

    //每一帧都会自动调用AnimationTimer 来刷新页面, 我们重写handle 使用paint来重新绘制
    private class Refresh extends AnimationTimer {
        @Override
        public void handle(long l) {
            if (running) {
                paint();
            }
        }
    }

    //设置键盘的监听器
    private class KeyProcess implements EventHandler<KeyEvent> {

        @Override
        public void handle(KeyEvent event) {
            KeyCode keyCode = event.getCode();
            if (event.getEventType() == KeyEvent.KEY_RELEASED) {
                if (keyCode.equals(KeyCode.P)) {
                    running = !running;
                }
                self.released(keyCode);
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                self.pressed(keyCode);
            }

        }
    }
}
