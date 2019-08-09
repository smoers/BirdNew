package org.bird.db.mapper;

import xyz.morphia.Datastore;

/**
 * Interaction avec la base de données
 */
public class Mapper implements IMapper {

    private Datastore datastore;

    public Mapper() {}

    @Override
    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }
    @Override
    public Datastore getDatastore() {
        return datastore;
    }

}
