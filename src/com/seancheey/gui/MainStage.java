package com.seancheey.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Seancheey on 27/05/2017.
 * GitHub: https://github.com/Seancheey
 */
public class MainStage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Amao Game");
        primaryStage.setScene(new Scene(root, 800.0, 600.0));
        primaryStage.show();
        Stages.INSTANCE.setStage(primaryStage);
    }

    public static void main(String args[]) {
        launch(args);
    }
}
