package org.bird.gui.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WaitingBarController implements Initializable {

    @FXML
    private ProgressBar waitingBar;
    @FXML
    private AnchorPane progressBarPane;
    private ServiceTask service;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void start(){
        waitingBar.setProgress(0);
        service = new ServiceTask();
        waitingBar.progressProperty().unbind();
        waitingBar.progressProperty().bind(service.progressProperty());
        new Thread(service).start();
    }

    public void stop(){
        service.setStop(true);
        //service.cancel();
        //waitingBar.setProgress(0);
    }

    private class ServiceTask extends Task<Boolean>{

        private long speed = 50;
        private int counterSize = 100;
        private boolean loop = true;
        private boolean stop = false;

        @Override
        protected Boolean call() throws Exception {
            while (!stop) {
                for (int i = 0; i < counterSize; i++) {
                    Thread.sleep(speed);
                    updateMessage("Task Completed : " + ((i * counterSize) + counterSize) + "%");
                    updateProgress(i + 1, counterSize);
                }
                if (!loop)
                    stop = true;
            }
            updateProgress(0,counterSize);
            return true;
        }

        public void setSpeed(long speed) {
            this.speed = speed;
        }

        public void setCounterSize(int counterSize) {
            this.counterSize = counterSize;
        }

        public void setLoop(boolean loop) {
            this.loop = loop;
        }

        public void setStop(boolean stop) {
            this.stop = stop;
        }
    }
}
