package org.bird.sandbox;

import java.net.MalformedURLException;
import java.net.URL;

public class _URL {

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("https","google.be","/");
        System.out.println(url.toExternalForm());
    }
}
