package com.clement.tank.controller;

import com.clement.tank.Director;
import com.clement.tank.util.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class IndexController {

    @FXML
    private ImageView startGame;

    @FXML
    void mouseClickStartGame(MouseEvent event) {
        Director.getInstance().gameStart();
        SoundEffect.play("/sound/done.wav");

    }

    @FXML
    void mouseEnterStartGame(MouseEvent event) {
        //设置鼠标进入时透明度变化
        startGame.setOpacity(0.7);
        SoundEffect.play("/sound/button.wav");

    }

    @FXML
    void mouseExitStartGame(MouseEvent event) {
        //设置鼠标离开时透明度回复
        startGame.setOpacity(1);

    }

}