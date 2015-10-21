package mines.nantes.dao;

/**
 * Created by Pierre on 21/10/2015.
 */
public class AbstractDAO<E> implements DAO<E> {

    private Class<E> typeClass;

    public AbstractDAO(Class<E> type)
    {
        this.typeClass = type;
    }

    @Override
    public E trouverParId(int id) {
        return Manager.getEntityManager().find(this.typeClass,id);
    }

    @Override
    public void sauvegarder(E entite) {
        Manager.getEntityManager().getTransaction().begin();
        Manager.getEntityManager().persist(entite);
        Manager.getEntityManager().getTransaction().commit();
    }

    @Override
    public void supprimer(E entite) {
        Manager.getEntityManager().getTransaction().begin();
        Manager.getEntityManager().remove(entite);
        Manager.getEntityManager().getTransaction().commit();
    }
}
