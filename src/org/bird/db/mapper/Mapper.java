package org.bird.db.mapper;

import org.bird.db.query.Paginator;
import xyz.morphia.Datastore;
import xyz.morphia.query.FindOptions;
import xyz.morphia.query.Query;

import java.util.List;

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
