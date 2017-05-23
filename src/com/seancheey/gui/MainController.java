package com.seancheey.gui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController extends Application {
    @FXML
    private TextField username, password;

    private static Stage PrimaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Amao Game");
        primaryStage.setScene(new Scene(root, 800, 600));
        PrimaryStage = primaryStage;
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public void login() throws Exception {
        System.out.println(username.getText() + "\n" + password.getText());
        Parent root = FXMLLoader.load(getClass().getResource("bot_edit.fxml"));
        PrimaryStage.setScene(new Scene(root, 800, 600));
    }

    public void register() {

    }

    public void setting() {

    }

    public void credit() {

    }
}
