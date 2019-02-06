package org.bird.sandbox;

import com.mongodb.MongoClient;
import org.bird.db.models.Author;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;
import xyz.morphia.query.Query;

import java.util.List;

public class _Morphia_Test_02 {

    public static void main(String[] args) {

        Morphia morphia = new Morphia();
        Datastore datastore = morphia.createDatastore(new MongoClient(), "bird");
        Query<Author> query = datastore.createQuery(Author.class);
        List<Author> authors = query.asList();
        System.out.println(authors.size());
        for(Author author : authors){
            System.out.println(author.getLastName());
            System.out.println(author.getCycles().size());
        }

    }
}
