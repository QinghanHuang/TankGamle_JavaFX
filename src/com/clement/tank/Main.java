package com.clement.tank;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Director.getInstance().init(stage);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
