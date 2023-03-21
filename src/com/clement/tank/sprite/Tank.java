package com.clement.tank.sprite;

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
                y-=5;
                break;
            case DOWN:
                y+=5;
                break;
            case LEFT:
                x-=5;
                break;
            case RIGHT:
                x+=5;
                break;
        }
        if(dir!=Direction.STOP){
            aimDir=dir;
        }

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
