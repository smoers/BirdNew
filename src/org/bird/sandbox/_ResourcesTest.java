package org.bird.sandbox;

import java.io.File;
import java.nio.file.Files;
import java.util.Locale;
import java.util.ResourceBundle;

public class _ResourcesTest {

    public static void main(String[] args) {

        Locale locale = new Locale("en");
        System.out.println();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.traduction", locale);
        System.out.println(resourceBundle.getString("yes"));
    }
}
