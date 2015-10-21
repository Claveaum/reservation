package mines.nantes.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

/**
 * Created by mclaveau on 20/10/15.
 */
@javax.servlet.annotation.WebServlet(urlPatterns = {"/reservation/*"})
public class HomeServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2unit");
        //EntityManager em = emf.createEntityManager();
        //em.getTransaction().commit();
        emf.close();

    }
}
