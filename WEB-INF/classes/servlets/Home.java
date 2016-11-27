package servlets;
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class Home extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
			response.getOutputStream().println("Hello World");
	}
}