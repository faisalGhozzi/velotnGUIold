package com.velotn.Ui;

import animatefx.animation.FadeIn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainUi.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Velo.tn");
        primaryStage.setScene(new Scene(root, 1024, 600));
        primaryStage.show();
        new FadeIn(root).play();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
