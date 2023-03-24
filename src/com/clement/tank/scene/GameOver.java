package com.clement.tank.scene;

import com.clement.tank.controller.GameOverController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class GameOver {
    public static void load(Stage stage,boolean success){
        try {
            FXMLLoader fxmlLoader=new FXMLLoader(Index.class.getResource("/fxml/game_over.fxml"));
            Parent root= fxmlLoader.load();
            GameOverController gameOverController=fxmlLoader.getController();
            if (success){
                gameOverController.setFlag();
            }
            stage.getScene().setRoot(root);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
