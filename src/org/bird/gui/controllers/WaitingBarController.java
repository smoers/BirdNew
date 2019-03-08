package org.bird.gui.controllers;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingBarController implements Initializable {

    @FXML
    private ProgressBar waitingBar;

    private WaitingBarTask task;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        task = new WaitingBarTask();
        waitingBar.setProgress(0);
        //waitingBar.progressProperty().unbind();
        waitingBar.progressProperty().bind(task.progressProperty());
        task.stop();
    }

    public void start(){
        task.restart();
        //new Thread(task).start();
    }

    public void stop(){
        task.stop();
    }

    private class WaitingBarTask extends Service<Void> {

        private boolean loop = true;
        private int count = 100;

        public void stop(){
            loop = false;
            this.cancel();
        }

        @Override
        protected Task<Void> createTask() {
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    /*while (loop){
                        for (int i=1; i<= count; i++){
                            updateProgress(i,count);
                            Thread.sleep(500);
                        }
                    }*/
                    return null;
                }
            };
            return null;
        }
    }
}
