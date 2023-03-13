package com.clement.tank.scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 新建Index类 用于载入主页
 * 1.载入fxml文件
 * 2.放入root
 * 3.stage.getScene(),放入Scene中
 *
 */
public class Index {
    public static void load(Stage stage){
        try {
            Parent root= FXMLLoader.load(Index.class.getResource("/fxml/index.fxml"));
        } catch (IOException e) {
            //陈鑫萍猪猪
            //Clement
            System.out.println(e.getMessage());;
        }
    }
}
