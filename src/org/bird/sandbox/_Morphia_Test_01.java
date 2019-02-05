package org.bird.sandbox;


import com.mongodb.MongoClient;
import xyz.morphia.Datastore;
import xyz.morphia.Morphia;

public class _Morphia_Test_01 {

    public static void main(String[] args) {
        final Morphia morphia = new Morphia();
        morphia.mapPackage("org.bird.db.models");
        final Datastore datastore = morphia.createDatastore(new MongoClient(), "bird");
        datastore.ensureIndexes();

    }

}
