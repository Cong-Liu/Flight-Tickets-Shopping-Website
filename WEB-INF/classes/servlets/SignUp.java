package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.Users;

public class SignUp extends HttpServlet {

	/**
	 * HTTP Post: sign up
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String username = request.getParameter("name");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		
		if(username == null || username.length() == 0) throw new ServletException("Please input username.");
		if(password == null || password.length() == 0) throw new ServletException("Please input password.");
		if(repassword == null || repassword.length() == 0) throw new ServletException("Please input confirm password.");
		
		username = username.trim();
		password = password.trim();
		repassword = repassword.trim();
		
		
		if(!password.equals(repassword))
			throw new ServletException("Sign up failure! Please enter your password again!");
		
		User user = new User(username, password);
		if(!Users.GetInstance().add(user))
			throw new ServletException("Your username " + username + " is not available. Please choose another username.");
		
		request.setAttribute("message", "Sign Up Success! Please login.");
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Message.jsp");
		dispatcher.forward(request, response);
	}
}
