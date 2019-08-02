package org.bird.sandbox;


import com.mongodb.MongoClient;
import org.bird.db.models.*;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

import java.util.Calendar;

public class _Morphia_Test_01 {

    public static void main(String[] args) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.bird.db.models");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "bird");
        datastore.ensureIndexes();
        User user = new User("smoers");
        user.setFirstName("Serge");
        user.setEmail("serge.moers@gmail.com");
        user.setLastName("Moers");
        user.setCreateDate(Calendar.getInstance().getTime());
        datastore.save(user);
        Illustrator illustrator = new Illustrator("Tisseron");
        illustrator.setFirstName("Yann");
        datastore.save(illustrator);
        Book book = new Book("Le sang des 7 Rois");
        book.setVolume(1);
       // book.setEditor("L'Atalante");
        book.setIsbn_10("2841726258");
        book.setPresentation("Deuxième jour de traque. Depuis le départ du château, la pluie n'a pas cessé de tomber. Je profite d'une roche en surplomb pour abriter le journal et écrire ce premier compte-rendu. Arrivés sur les alpages, nous avons suivi la crête pour trouver des indices. Rien ne nous avait préparés à ce que nous avons trouvé là. Un autre campement avait été édifié à cinquante pas à vol d'oiseau du premier et tout indique qu'alors que nous pensions notre retard considérable, ses occupants s'en étaient allés quelques heures auparavant.\n" +
                "\n" +
                "Entrez dans l'univers des 7 royaumes où sévit l'inquisition, et découvrez le secret de l'origine du sang bleu.\n" +
                "Un événement en fantasy française. Un premier roman, un coup de maître.");
        book.setCreateDate(Calendar.getInstance().getTime());
        book.getIllustrators().add(illustrator);
        book.setUser(user);
        datastore.save(book);
        Cycle cycle = new Cycle("Le sans des 7 Rois");
        cycle.setVolumeNumber(7);
        cycle.getBooks().add(book);
        cycle.setCreateDate(Calendar.getInstance().getTime());
        cycle.setUser(user);
        datastore.save(cycle);
        Author author = new Author("Goddyn");
        author.setFirstName("Régis");
        author.setCreateDate(Calendar.getInstance().getTime());
        author.setUser(user);
        cycle.getAuthors().add(author);
        author.getCycles().add(cycle);
        datastore.save(author);
        datastore.save(cycle);

    }

}
