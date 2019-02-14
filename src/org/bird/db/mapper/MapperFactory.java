package org.bird.db.mapper;

import com.mongodb.MongoClient;
import org.bird.configuration.Configuration;
import org.bird.configuration.ConfigurationBuilder;
import org.bird.configuration.exceptions.ConfigurationException;
import org.bird.db.exceptions.DBException;
import xyz.morphia.Morphia;

/**
 * Fourni des objets destinés aux interactions avec la base de données
 */
public class MapperFactory {
    private Morphia morphia = new Morphia();
    private String databaseName = null;
    private static MapperFactory ourInstance = null;

    /**
     * Retourne l'instance de la classe
     * @return
     */
    public static MapperFactory getInstance() throws DBException {
        if (ourInstance == null){
            ourInstance = new MapperFactory();
        }
        return ourInstance;
    }

    /**
     * Constructeur privé
     */
    private MapperFactory() throws DBException {
        try {
            databaseName = ConfigurationBuilder.getInstance().getElement("global::global.database.name").getAsString();
        } catch (ConfigurationException e) {
            throw new DBException(9001);
        }
    }

    /**
     * Retourne le nom de la base de donnée
     * @return
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     *
     * @param databaseName
     */
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    /**
     * Retourne un objet mapper pour les interactions avec la base de données
     * @return
     * @throws DBException
     */
    public Mapper getMapper() throws DBException {
        Mapper mapper = null;
        if (databaseName != null)
            mapper = new Mapper(morphia.createDatastore(new MongoClient(), databaseName));
        else
            throw new DBException(9000);
        return mapper;
    }
}
