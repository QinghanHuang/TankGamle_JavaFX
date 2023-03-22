package com.clement.tank.sprite;

import com.clement.tank.Director;
import com.clement.tank.scene.GameScene;
import com.clement.tank.util.Direction;

import com.clement.tank.util.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;


public class Tank extends Role {
    Direction aimDir;
    boolean keyUp, keyDown, keyLeft,keyRight;

    public Tank(double x, double y, Group group, Direction dir, Direction aimDir, GameScene gameScene) {
        super(x, y, 60, 60, group, dir, gameScene);
        this.aimDir = aimDir;
        speed=5;


        if (group.equals(Group.GREEN)) {
            imageMap.put("UP", new Image("image/tank-green-up.png"));
            imageMap.put("DOWN", new Image("image/tank-green-down.png"));
            imageMap.put("LEFT", new Image("image/tank-green-left.png"));
            imageMap.put("RIGHT", new Image("image/tank-green-right.png"));
        } else {
            imageMap.put("UP", new Image("image/tank-red-up.png"));
            imageMap.put("DOWN", new Image("image/tank-red-down.png"));
            imageMap.put("LEFT", new Image("image/tank-red-left.png"));
            imageMap.put("RIGHT", new Image("image/tank-red-right.png"));

        }
    }

    public void pressed(KeyCode keyCode) {
        switch (keyCode) {
            case UP:
                keyUp = true;
                break;
            case DOWN:
                keyDown = true;
                break;
            case LEFT:
                keyLeft = true;
                break;
            case RIGHT:
                keyRight = true;
                break;
        }
        redirect();
    }

    public void released(KeyCode keyCode) {
        switch (keyCode) {
            case SPACE:
                fire();
                break;
            case UP:
                keyUp = false;
                break;
            case DOWN:
                keyDown = false;
                break;
            case LEFT:
                keyLeft = false;
                break;
            case RIGHT:
                keyRight = false;
                break;
        }
        redirect();
    }

    public void fire(){
        double bx=x;
        double by=y;
        switch (aimDir){
            case UP:
                bx=x+25;
                by=y ;
                break;
            case DOWN:
                bx = x+25;
                by = y+50;
                break;
            case LEFT:
                bx = x;
                by = y+25;
                break;
            case RIGHT:
                bx = x+50;
                by = y+25;
                break;
        }
        Bullet bullet=new Bullet(bx,by,group,aimDir,gameScene);
        gameScene.getBullets().add(bullet);

    }

    public void redirect(){
        if(keyUp&&!keyDown&&!keyLeft&&!keyRight) dir=Direction.UP;
        else if(!keyUp&&keyDown&&!keyLeft&&!keyRight) dir=Direction.DOWN;
        else if(!keyUp&&!keyDown&&keyLeft&&!keyRight) dir=Direction.LEFT;
        else if(!keyUp&&!keyDown&&!keyLeft&&keyRight) dir=Direction.RIGHT;
        else if(!keyUp&&!keyDown&&!keyLeft&&!keyRight) dir=Direction.STOP;

    }


    @Override
    public void move() {
        switch (dir){
            case UP :
                y-=speed;
                break;
            case DOWN:
                y+=speed;
                break;
            case LEFT:
                x-=speed;
                break;
            case RIGHT:
                x+=speed;
                break;
        }
        if(dir!=Direction.STOP){
            aimDir=dir;
        }
//      设定运动的最大值和最小值
        if (x<0) x=0;
        if (y<0) y=0;
        if(x> Director.WIDTH-width) x=Director.WIDTH-width;
        if(y> Director.HEIGHT-height-30) y=Director.HEIGHT-width-30;

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        switch (aimDir){
            case UP :
                image=imageMap.get("UP");
                break;
            case DOWN:
                image=imageMap.get("DOWN");
                break;
            case LEFT:
                image=imageMap.get("LEFT");
                break;
            case RIGHT:
                image=imageMap.get("RIGHT");
                break;
        }
        super.paint(graphicsContext);
        move();
    }

    @Override
    public boolean impactCheck(Sprite sprite) {
        return false;
    }
}
