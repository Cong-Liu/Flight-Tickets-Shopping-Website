package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.User;
import models.Users;

public class Login extends HttpServlet {


	/**
	 * HTTP Post: check username and password to login
	 * @throws ServletException 
	 * @throws IOException 
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
			String username = request.getParameter("name");
			String password = request.getParameter("password");
			
			if(username == null || username.length() == 0) throw new ServletException("Please input username.");
			if(password == null || password.length() == 0) throw new ServletException("Please input password.");
			
			username = username.trim();
			password = password.trim();
			
			User user = Users.GetInstance().findByName(username);
			if(user == null || !user.getPswd().equals(password))
				try {
					throw new Exception("Login Failure! Username and/or password is incorrect.");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			request.getSession().setAttribute("user", user);
			request.setAttribute("message", "<script>setTimeout(function(){location.href='Home.jsp'}, 2);</script>");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Message.jsp");
			dispatcher.forward(request, response);

	}
}
