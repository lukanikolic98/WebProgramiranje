package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDAO;
import dao.ResultDAO;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// uzmem parametre, i trenutnog usera. Uradim search nad Users.dao i popuni mu se usersResult lista.
		// Saljem na home.jsp gde ce se pokazati usersResult lista
		ServletContext context = getServletContext();
		UserDAO users = (UserDAO) context.getAttribute("users");
		User user = (User) context.getAttribute("user");
		String searchTerm = request.getParameter("searchTerm");
		if(searchTerm != null) {
			// search bar search
			request.setAttribute("searchResult", new ResultDAO(users.searchUsers(searchTerm, user)));
			RequestDispatcher disp = request.getRequestDispatcher("HomeServlet");
			disp.forward(request, response);
			return;
		}
		// redovan search
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		users.searchUsers(firstName,  lastName, startDate, endDate, user);
		RequestDispatcher disp = request.getRequestDispatcher("HomeServlet");
		disp.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
