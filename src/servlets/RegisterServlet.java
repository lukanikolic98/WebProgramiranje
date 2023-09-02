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
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
    	super.init();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			response.sendRedirect("HomeServlet");
			return;
		}
		RequestDispatcher disp = request.getRequestDispatcher("/JSP/register.jsp");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String firstname = request.getParameter("firstname");
    	String lastname = request.getParameter("lastname");
    	String email = request.getParameter("email");
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String role = "User";
    	ServletContext context = getServletContext();
    	UserDAO users = (UserDAO) context.getAttribute("users");
    	User user = users.find(username);
    	
    	if (user != null) {
	    	request.setAttribute("err", "Username Taken! Try again!");
	    	doGet(request, response);
	    	return;
    	}
    	// napraviti novoregistrovanog USERA i zapisati izmene u fajl
    	user = new User( firstname,  lastname,  email,  username,  password, role);
    	users.add(user);
    	users.saveUsers();
    	HttpSession session = request.getSession();
    	session.setAttribute("user", user);
    	response.sendRedirect("HomeServlet");
	}

}
