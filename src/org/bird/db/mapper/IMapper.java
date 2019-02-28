package org.bird.db.mapper;

import xyz.morphia.Datastore;

public interface IMapper {


    public void setDatastore(Datastore datastore);

    public Datastore getDatastore();

}
