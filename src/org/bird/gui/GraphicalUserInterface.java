package org.bird.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.bird.gui.resources.images.ImageProvider;

public class GraphicalUserInterface extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/org/bird/gui/resources/views/dashboard.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setTitle("Bird Fly");
        primaryStage.getIcons().add(ImageProvider.getLogoImage());
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/org/bird/gui/resources/css/fxcss.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
}
