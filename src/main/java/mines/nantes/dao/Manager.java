package mines.nantes.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by Pierre on 21/10/2015.
 */
public class Manager {
    private static Manager ourInstance = new Manager();

    private static EntityManager em = Persistence.createEntityManagerFactory("h2unit").createEntityManager();

    public static Manager getInstance() {
        return ourInstance;
    }

    private Manager() {
    }

    public static EntityManager getEntityManager() {
        return em;
    }
}
