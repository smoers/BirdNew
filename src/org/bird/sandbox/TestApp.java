package org.bird.sandbox;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class TestApp extends Application {

    private Stage progressStage;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Button btn = new Button("start task");

        TaskService service = new TaskService();
        service.setOnScheduled(e -> progressStage.show());
        //service.setOnSucceeded(e -> progressStage.hide());

        ProgressBar progressBar = new ProgressBar();
        progressBar.progressProperty().bind(service.progressProperty());

        progressStage = new Stage();
        progressStage.setScene(new Scene(new StackPane(progressBar), 300, 300));
        progressStage.setAlwaysOnTop(true);

        btn.setOnAction(e -> service.restart());

        primaryStage.setScene(new Scene(new StackPane(btn), 300, 300));
        primaryStage.show();
    }

    private class TaskService extends Service<Void> {

        @Override
        protected Task<Void> createTask() {
            Task<Void> task = new Task<Void>() {

                @Override
                protected Void call() throws Exception {

                    for (int p = 0; p < 100; p++) {
                        Thread.sleep(40);
                        updateProgress(p, 100);
                    }
                    return null;
                }
            };
            return task;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}