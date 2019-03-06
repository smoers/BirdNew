package org.bird.db.mapper;

import org.bird.db.exceptions.DBException;
import org.bird.db.query.Paginator;
import org.bird.utils.Utils;
import xyz.morphia.Datastore;
import xyz.morphia.query.FindOptions;
import xyz.morphia.query.Query;

import java.util.List;
import java.util.Locale;

/**
 * Cette classe mapper est destinée aux interactions avec la DB
 * à la demande d'un objet Paginator
 */
public class MapperPaginator implements IMapper {

    private Datastore datastore = null;

    /**
     * Contructeur
     */
    public MapperPaginator() { }

    @Override
    public void setDatastore(Datastore datastore) {
        this.datastore = datastore;
    }

    @Override
    public Datastore getDatastore() {
        return datastore;
    }

    /**
     * Charge un objet Paginator avec le resultat du query
     * @param paginator
     * @param <T>
     * @return
     * @throws DBException
     */
    public <T> Paginator loadPaginator(Paginator<T> paginator) throws DBException {

        if (datastore != null) {
            //l'ojbet Paginator dispose t il d'un query
            Query<T> query = null;
            if (paginator.getQuery() == null) {
                query = datastore.createQuery(paginator.getEntityClass());
            } else {
                query = paginator.getQuery();
            }
            //Permet de calcul la valeur Skip au départ du numéro de page
            int skipValue = (paginator.getPage() - 1) * paginator.getItemsByPage();
            List<T> list = query.asList(new FindOptions().limit(paginator.getItemsByPage()).skip(skipValue));
            Long l = query.count();
            paginator.setPages(l.intValue());
            paginator.setList(list);
            System.out.println(Utils.roundUp(l.intValue(),paginator.getItemsByPage()));
            return paginator;
        } else {
            throw new DBException(9000);
        }
    }
}
