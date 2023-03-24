package com.clement.tank.scene;

import com.clement.tank.Director;
import com.clement.tank.sprite.*;
import com.clement.tank.util.Direction;
import com.clement.tank.util.Group;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
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
    private Tank self =null;
    //储存子弹list
    private List<Bullet> bullets=new ArrayList<>();
    //储存敌方坦克
    private List<Tank> enemies=new ArrayList<>();
    //储存爆炸效果
    private List<Explore> explores=new ArrayList<>();
    //储存box
    private List<Box> boxes=new ArrayList<>();
    //储存Rock
    private List<Rock> rocks=new ArrayList<>();
    //储存Tree
    private List<Tree> trees=new ArrayList<>();


    public List<Bullet> getBullets() {
        return bullets;
    }

    public List<Tank> getEnemies(){
        return enemies;
    }

    public List<Explore> getExplores() {
        return explores;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public List<Rock> getRocks() {
        return rocks;
    }

    public List<Tree> getTrees() {
        return trees;
    }

    private void paint() {
        background.paint(graphicsContext);
        self.paint(graphicsContext);
        self.impact(enemies);
        self.impact(boxes);
        self.impact(rocks);

        for (int i = 0; i <bullets.size() ; i++) {
            Bullet bullet=bullets.get(i);
            bullet.paint(graphicsContext);
            bullet.impactTank(enemies);
            bullet.impactBox(boxes);
            bullet.impactRock(rocks);
            bullet.impactTank(self);

        }
        for (int i = 0; i <enemies.size() ; i++) {
            Tank enemyTank=enemies.get(i);
            enemyTank.paint(graphicsContext);
            enemyTank.impact(boxes);
            enemyTank.impact(rocks);
            enemyTank.impact(enemies);
            enemyTank.impact(self);

        }

        for (int i = 0; i <explores.size() ; i++) {
            explores.get(i).paint(graphicsContext);
        }

        for (int i = 0; i <boxes.size() ; i++) {
            boxes.get(i).paint(graphicsContext);
        }
        for (int i = 0; i <rocks.size() ; i++) {
            rocks.get(i).paint(graphicsContext);
        }
        for (int i = 0; i <trees.size() ; i++) {
            trees.get(i).paint(graphicsContext);
        }

        graphicsContext.setFont(new Font(20));
        graphicsContext.fillText("Enemies: "+enemies.size(),800,60);
        graphicsContext.fillText("Bullets: "+bullets.size(),800,90);

        if(!self.isAlive()){
            Director.getInstance().gameOver(false);
        }else if(enemies.size()==0){
            Director.getInstance().gameOver(true);
        }

    }

    public void init(Stage stage) {
        AnchorPane root = new AnchorPane(canvas);
        stage.getScene().setRoot(root);
        stage.getScene().setOnKeyPressed(keyProcess);
        stage.getScene().setOnKeyReleased(keyProcess);
        running = true;
        //初始玩家坦克
        self = new Tank(400, 500, Group.GREEN, Direction.STOP, Direction.UP, this);
        //初始Sprite
        initSprite();
        refresh.start();
    }

    private void initSprite(){
        for (int i = 0; i < 6; i++) {
            Tank tank=new Tank(200+i*100,100,Group.RED,Direction.STOP,Direction.DOWN,this);
            enemies.add(tank);
        }

        for (int i = 0; i < 20; i++) {
            Box box=new Box(100+i*31,200);
            Box box1=new Box(100+i*31,232);
            boxes.add(box);
            boxes.add(box1);
        }

        for (int i = 0; i <3 ; i++) {
            Rock rock=new Rock(100+i*300,300);
            rocks.add(rock);
        }

        for (int i = 0; i <3 ; i++) {
            Tree tree=new Tree(50+i*100,400);
            trees.add(tree);
        }


    }


    public void clear(Stage stage) {
        stage.getScene().removeEventHandler(KeyEvent.KEY_RELEASED, keyProcess);
        stage.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, keyProcess);
        refresh.stop();
        self=null;
        bullets.clear();
        enemies.clear();
        explores.clear();
        boxes.clear();
        rocks.clear();
        trees.clear();


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
                if (self!=null) {
                    self.released(keyCode);
                }
            } else if (event.getEventType() == KeyEvent.KEY_PRESSED) {
                if (self!=null) {
                    self.pressed(keyCode);
                }
            }

        }
    }
}
