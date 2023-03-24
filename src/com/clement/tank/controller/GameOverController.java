package com.clement.tank.controller;

import com.clement.tank.Director;
import com.clement.tank.util.SoundEffect;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class GameOverController {

    @FXML
    private ImageView flag;

    @FXML
    private ImageView returnButton;

    @FXML
    void returnClick(MouseEvent event) {
        Director.getInstance().toIndex() ;
        SoundEffect.play("/sound/done.wav");

    }

    @FXML
    void returnEnter(MouseEvent event) {
        //设置鼠标进入时透明度变化
        returnButton.setOpacity(0.7);
        SoundEffect.play("/sound/button.wav");

    }

    @FXML
    void returnExit(MouseEvent event) {
        //设置鼠标离开时透明度回复
        returnButton.setOpacity(1);

    }
    public void setFlag(){
        flag.setImage(new Image("image/Win.png"));
    }

}
