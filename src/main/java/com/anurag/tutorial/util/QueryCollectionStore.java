package com.anurag.tutorial.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hsqldb.lib.Storage;

/**
 * @author Anurag
 * 
 * concept:-
 *
 */
public class QueryCollectionStore<T> {

    private static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    private T entity;
    private Session session;

    public void beginTransaction() {
        session = QueryCollectionStore.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void commit () {
        session.getTransaction().commit();
    }

    public QueryCollectionStore(T entity) {
        this.entity = entity;
    }

    public void update(T entity) {
        session.update(entity);
    }

    public Long insert(T entity) {
        return (Long) session.save(entity);
    }

    public void delete(T entity) {
        session.delete(entity);
    }

    // This call will issue a warning about the unchecked cast,
    // but we know the value returned will be of the right type because
    // we specify the entity (T) class in the call.
    //
    // Note that "get" will return a null if no value with this id fails
    @SuppressWarnings (value="unchecked")
    public T getById(Long id) {
        return (T) session.get(entity.getClass(), id);

    }
}
