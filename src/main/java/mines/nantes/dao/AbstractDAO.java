package mines.nantes.dao;

import mines.nantes.Exception.UniciteException;

import javax.persistence.PersistenceException;

/**
 * Created by Pierre on 21/10/2015.
 */
public class AbstractDAO<E> implements DAO<E> {

    private Class<E> typeClass;

    public AbstractDAO(Class<E> type) {
        this.typeClass = type;
    }

    @Override
    public E trouverParId(int id) {
        return Manager.getEntityManager().find(this.typeClass, id);
    }

    @Override
    public void sauvegarder(E entite) throws UniciteException {
        try {
            Manager.getEntityManager().getTransaction().begin();
            Manager.getEntityManager().persist(entite);
            Manager.getEntityManager().getTransaction().commit();
        } catch (PersistenceException e) {
            throw new UniciteException();
        }
    }

    @Override
    public void supprimer(E entite) {
        Manager.getEntityManager().getTransaction().begin();
        Manager.getEntityManager().remove(entite);
        Manager.getEntityManager().getTransaction().commit();
    }
}
