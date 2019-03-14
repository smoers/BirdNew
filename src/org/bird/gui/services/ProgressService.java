package org.bird.gui.services;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ProgressService extends Service<Void> {
    @Override
    protected Task<Void> createTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for(int i = 1;i<=100;i++){
                    updateProgress(i,100);
                    updateMessage(String.valueOf(i));
                    System.out.println(i);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
    }
}
