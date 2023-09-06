package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
    	super.init();
    	ServletContext context = getServletContext();
    	String contextPath = context.getRealPath("");
    	// Dodaju se korisnici u kontekst kako bi mogli servleti da rade sa njima
    	context.setAttribute("users", new UserDAO(contextPath));
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			if(user.getUsername() != null) {
				response.sendRedirect("HomeServlet");
				return;
			}		
		}
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/login.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	ServletContext context = getServletContext();
    	UserDAO users = (UserDAO) context.getAttribute("users");
    	User user = users.find(username, password);
    	if (user == null) {
    	request.setAttribute("err", "Pogrešno korisnièko ime / lozinka");
    	doGet(request, response);
    	return;
    	}
    	HttpSession session = request.getSession();
    	session.setAttribute("user", user);
    	response.sendRedirect("HomeServlet");
	}

}
