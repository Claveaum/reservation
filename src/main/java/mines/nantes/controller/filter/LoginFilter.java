package mines.nantes.controller.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class LoginFilter implements javax.servlet.Filter {
    protected ServletContext servletContext;

    public void init(FilterConfig filterConfig) {
        servletContext = filterConfig.getServletContext();
    }

    public void doFilter(
            ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        RequestDispatcher dispatcher;

        if (!isAuth(req)) {
            dispatcher=request.getRequestDispatcher("/pages/login.jsp");
            dispatcher.forward(request,response);
            return; //break filter chain, requested JSP/servlet will not be executed
        }

        //propagate to next element in the filter chain, ultimately JSP/ servlet gets executed
        chain.doFilter(request, response);
    }

    /**
     * logic to accept or reject access to the page, check log in status
     * @return true when authentication is deemed valid
     */
    protected abstract boolean isAuth(HttpServletRequest req);

}