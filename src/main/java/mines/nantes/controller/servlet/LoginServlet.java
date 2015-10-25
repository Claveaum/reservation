package mines.nantes.controller.servlet;

import mines.nantes.dao.UtilisateurDAO;
import mines.nantes.entity.Utilisateur;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mclaveau on 24/10/15.
 */
@WebServlet(urlPatterns = "/loginCheck")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        RequestDispatcher dispatcher;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        Utilisateur utilisateur = utilisateurDAO.getUtilisateurParLoginPassword(login,password);
        if(null != utilisateur){
            request.getSession().setAttribute("user",utilisateur);
            response.sendRedirect("/reservation/reservation");
        }else{
            dispatcher=request.getRequestDispatcher("/WEB-INF/html/login.jsp");
            dispatcher.forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
