package org.bird.db.mapper;

import org.bird.db.query.Paginator;
import xyz.morphia.Datastore;
import xyz.morphia.query.FindOptions;
import xyz.morphia.query.Query;

import java.util.List;

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

    public <T> Paginator loadPaginator(Paginator<T> paginator){

        //l'ojbet Paginator dispose t il d'un query
        Query<T> query = null;
        if (paginator.getQuery() == null){
            query = datastore.createQuery(paginator.getEntityClass());
        } else {
            query = paginator.getQuery();
        }
        //Permet de calcul la valeur Skip au départ du numéro de page
        int skipValue = (paginator.getPage()-1)*paginator.getItemsByPage();
        List<T> list = query.asList(new FindOptions().limit(paginator.getItemsByPage()).skip(skipValue));
        paginator.setPages(query.count());
        paginator.setList(list);
        return paginator;
    }

}
