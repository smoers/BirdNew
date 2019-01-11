package org.bird.gui.resources.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageProvider {

    private Image image;
    private ImageView imageView;

    public ImageProvider(String path) {
        image = new Image(getClass().getResource(path).toExternalForm());
        imageView = new ImageView(image);
    }

    public ImageProvider(ImageContext imageContext){
        image = new Image(getClass().getResource(imageContext.getContext()).toExternalForm());
        imageView = new ImageView(image);
    }

    public Image getImage() {
        return image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    /**
     *
     */
    public enum ImageContext {

        ALERT_16("/images/blue/high-importance-16.png"),
        ALERT_24("/images/blue/high-importance-24.png"),
        ALERT_32("/images/blue/high-importance-32.png"),
        MESSAGE_16("/images/blue/message-2-16.png"),
        MESSAGE_24("/images/blue/message-2-24.png"),
        MESSAGE_32("/images/blue/message-2-32.png"),
        INFO_16("/images/blue/info-16.png"),
        INFO_24("/images/blue/info-24.png"),
        INFO_32("/images/blue/info-32.png"),
        QUESTION_16("/images/blue/question-mark-2-16.png"),
        QUESTION_24("/images/blue/question-mark-2-24.png"),
        QUESTION_32("/images/blue/question-mark-2-32.png");


        private String context;

        ImageContext(String context){
            this.context = context;
        }

        public String getContext(){
            return context;
        }
    }
}
