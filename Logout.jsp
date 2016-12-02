<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- 
<h2>You have been successfully log out!</h2>
<h4>Please use navigation bar or side bar to see product list.</h4>
 -->
<%
	request.getSession().removeAttribute("user");
	request.getSession().removeAttribute("cart");
	response.sendRedirect("Home.jsp");
%>
