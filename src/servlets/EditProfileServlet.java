package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;
import dao.UserDAO;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
			if(user.getUsername() != null) {
				RequestDispatcher disp = request.getRequestDispatcher("/JSP/editProfile.jsp");
				disp.forward(request, response);
				return;
			}		
		}
		response.sendRedirect("LoginServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
    	String firstName = request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String email = request.getParameter("email");
    	String oldPassword = request.getParameter("oldPassword");
    	String newPassword = request.getParameter("newPassword");
    	String confirmNewPassword = request.getParameter("confirmNewPassword");
    	String gender = request.getParameter("gender");
    	Boolean publicStatus = Boolean.parseBoolean(request.getParameter("publicStatus"));
		UserDAO users = (UserDAO) getServletContext().getAttribute("users");
		// Ako se menja sifra i ne poklapaju se
    	if(newPassword != null && !newPassword.equals(confirmNewPassword)) {
    		request.setAttribute("err", "Passwords dont match!");
    		doGet(request, response);
    		return;
    	}
    	// Ako nije uneta stara sifra ili je pogresna
    	if(oldPassword == null || users.find(user.getUsername(), oldPassword) == null) {
    		request.setAttribute("err", "Your old password is incorrect!");
    		doGet(request, response);
    		return;
		// Ako korisnik postoji i dobra je sifra
    	} else {
    		User oldUser = users.find(user.getUsername(), oldPassword);
    		// sacuvaj izmene
    		oldUser.setFirstName(firstName);
    		oldUser.setLastName(lastName);
    		oldUser.setEmail(email);
    		oldUser.setGender(gender);
    		oldUser.setPublicStatus(publicStatus);
    		user.setFirstName(firstName);
    		user.setLastName(lastName);
    		user.setEmail(email);
    		user.setGender(gender);
    		user.setPublicStatus(publicStatus);
    		// Ako je promenjena sifra, sacuvaj inace ignorisi. Vec je provereno da li se podudaraju nova i kontrolna sifra
    		if(!newPassword.equals("")) {
    			oldUser.setPassword(newPassword);
    			user.setPassword(newPassword);    			
    		}
    		users.saveUsers();
    		request.setAttribute("success", "Changes saved successfully!");
    	}
		doGet(request, response);
	}

}
