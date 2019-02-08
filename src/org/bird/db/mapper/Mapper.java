package org.bird.db.mapper;

import xyz.morphia.Datastore;

/**
 * Interaction avec la base de données
 */
public class Mapper {
    private Datastore datastore;

    public Mapper(Datastore datastore) {
        this.datastore = datastore;
    }

    public Datastore getDatastore() {
        return datastore;
    }


}
