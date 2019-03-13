package org.bird.gui.controllers;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
        System.out.println("start");
        service = new ServiceTask();
        waitingBar.progressProperty().unbind();
        waitingBar.progressProperty().bind(service.progressProperty());
        Thread thread = new Thread(service);
        thread.start();
    }

    public void stop(){
        System.out.println("stop");
        service.setStop(true);
    }

    private class ServiceTask extends Task<Boolean>{

        private long speed = 50;
        private int counterSize = 5;
        private boolean loop = true;
        private boolean stop = false;

        @Override
        protected Boolean call() throws Exception {
            while (!stop) {
                for (int i = 0; i < counterSize; i++) {
                    Thread.sleep(speed);
                    updateMessage("% " + i);
                    updateProgress(i + 1, counterSize);
                    System.out.println(i + 1);
                    if(stop)
                        break;
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
