package org.bird.db.query;

import xyz.morphia.query.Query;

import java.util.List;

public class Paginator<T> {

    private Query<T> query;
    private Class<T> entityClass;

    private List<T> list = null;
    private int page = 1;
    private long pages = 0;
    private int itemsByPage;

    /**
     * Contructeur
     */
    public Paginator(int page, int itemByPage, Class<T> entityClass) {
        this.page = page;
        this.itemsByPage = itemByPage;
    }

    /**
     * Contructeur
     */
    public Paginator(int page, int itemByPage, Query<T> query, Class<T> entityClass) {
        this.page = page;
        this.itemsByPage = itemByPage;
        this.query = query;
    }


    /**
     * Retourne la liste des objets
     * @return
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Charge la liste des objets
     * @param list
     */
    public void setList(List<T> list) {
        this.list = list;
    }

    /**
     * Retourne la page actuelle
     * @return
     */
    public int getPage() {
        return page;
    }

    /**
     * Retourne le nombre de page de la collection
     * @return
     */
    public long getPages() {
        return pages;
    }

    /**
     * Charge le nombre de page de la collection
     * @param pages
     */
    public void setPages(long pages) {
        this.pages = pages;
    }

    /**
     * Retourne le nombre d'items par page
     * @return
     */
    public int getItemsByPage() {
        return itemsByPage;
    }

    public Query<T> getQuery() {
        return query;
    }

    public void setQuery(Query<T> query) {
        this.query = query;
    }

    public Class<T> getEntityClass(){
        return entityClass;
    }

}
