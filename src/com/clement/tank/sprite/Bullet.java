package com.clement.tank.sprite;

import com.clement.tank.Director;
import com.clement.tank.scene.GameScene;
import com.clement.tank.util.Direction;
import com.clement.tank.util.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Bullet extends Role{
    public Bullet(double x, double y, Group group, Direction dir, GameScene gameScene) {
        super(x, y,0, 0, group, dir, gameScene);
        speed=10;

        if(dir.equals(Direction.UP)||dir.equals(Direction.DOWN)){
            width=10;
            height=22;
        }else {
            width=22;
            height=10;
        }
        if(group.equals(Group.GREEN)){
            switch (dir){
                case UP :
                    image=new Image("image/bullet-green-up.png");
                    break;
                case DOWN:
                    image=new Image("image/bullet-green-down.png");
                    break;
                case LEFT:
                    image=new Image("image/bullet-green-left.png");
                    break;
                case RIGHT:
                    image=new Image("image/bullet-green-right.png");
                    break;
            }
        }else{
            switch (dir){
                case UP :
                    image=new Image("image/bullet-red-up.png");
                    break;
                case DOWN:
                    image=new Image("image/bullet-red-down.png");
                    break;
                case LEFT:
                    image=new Image("image/bullet-red-left.png");
                    break;
                case RIGHT:
                    image=new Image("image/bullet-red-right.png");
                    break;
            }
        }



    }

    @Override
    public void move() {
        switch (dir) {
            case UP -> y -= speed;
            case DOWN -> y += speed;
            case LEFT -> x -= speed;
            case RIGHT -> x += speed;
        }

        //      设定运动的最大值和最小值
//        if (x<0) x=0;
//        if (y<0) y=0;
//        if(x> Director.WIDTH-width) x=Director.WIDTH-width;
//        if(y> Director.HEIGHT-height-30) y=Director.HEIGHT-width-30;

    }

    @Override
    public void paint(GraphicsContext graphicsContext) {
        super.paint(graphicsContext);
        move();
    }

    @Override
    public boolean impactCheck(Sprite sprite) {
        return false;
    }
}
