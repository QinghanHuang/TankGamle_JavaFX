package com.clement.tank.sprite;

import com.clement.tank.scene.GameScene;
import com.clement.tank.util.Direction;

import com.clement.tank.util.Group;
import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public abstract class Role extends Sprite {
    boolean alive=true;
    Group group;
    Direction dir;
    double speed;
    Map<String,Image> imageMap=new HashMap<>();

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public Role(double x, double y, double width, double height, Group group, Direction dir, GameScene gameScene) {
        super(null, x, y, width, height, gameScene);
        this.group = group;
        this.dir = dir;
    }
    public abstract void move();


}
