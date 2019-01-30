package org.bird.gui.resources.images;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;

/**
 * création des objects Image & ImageView au départ des fichiers images
 */
public class ImageProvider {

    private Image image;
    private ImageView imageView;
    private ConfigurationBuilder configurationBuilder = ConfigurationBuilder.getInstance();

    /**
     * Accès rapide au logo
     * @return
     */
    public static Image getLogoImage() {
        return (new ImageProvider()).getImage();
    }
    /**
     * Accès rapide au logo
     * @return
     */
    public static ImageView getLogoImageView() {
        return (new ImageProvider()).getImageView();
    }

    /**
     * Contructeur pour le logo
     */
    public ImageProvider(){
        Configuration configuration = configurationBuilder.get("layout");
        String path = configuration.get("layout.logo").getAsString();
        image = new Image(getClass().getResource(path).toExternalForm());
        imageView = new ImageView(image);
    }

    /**
     * Contructeur depuis un fichier image
     * @param path
     */
    public ImageProvider(String path) {
        image = new Image(getClass().getResource(path).toExternalForm());
        imageView = new ImageView(image);
    }

    /**
     * Constructeur depuis un fichier prédéfini
     * @param imageContext
     */
    public ImageProvider(ImageContext imageContext){
        image = new Image(getClass().getResource(imageContext.getContext()).toExternalForm());
        imageView = new ImageView(image);
    }

    /**
     * retourne un objet Image du fichier image
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * retourne un objet ImageView du fichier image
     * @return
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Images prédéfinies
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
