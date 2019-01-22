package org.bird.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GraphicalUserInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/bird/gui/resources/views/dashboard.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Bird Fly");
        Scene scene = new Scene(root, 300, 275);
        scene.getStylesheets().add(getClass().getResource("/org/bird/gui/resources/css/fxcss.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
