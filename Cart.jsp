<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="Header.jsp"%>
<%@ page import="java.util.List,database.*"%>

<!-- import packages and classes needed by the scripts -->
 
<%if (user == null) throw new Exception("Please login before you can access your shopping cart.");
			
	//read shopping cart from session
	Order order = null;
	session = request.getSession();
	if (session.getAttribute("cart") == null)  {
		order = new Order(user.getName());
		session.setAttribute("cart", order);
		//throw new Exception("Your Shopping cart is empty.");
	}
	else order = (Order)session.getAttribute("cart");
	
	//check if user is adding a new item to cart
	String pId = request.getParameter("add");
	if(pId != null) {
		Flight flight = Flights.GetInstance().findById(Integer.parseInt(pId));
		if(flight == null) throw new Exception("Flight ID " + pId + " is not found.");
		order.addItem(flight);
	}
	
	//check if user is deleting an item from cart
	pId = request.getParameter("del");
	if(pId != null) {
		order.removeItem(Integer.parseInt(pId));
	}
	
	List<Flight> items = order.getItems();
	if(items.size() == 0) throw new Exception("Your Shopping cart is empty.");
	
	//generate order item list
	StringBuilder content = new StringBuilder();%>
	<div class = "banner-bottom">
	<div class='container'>
	<div class = 'faqs-top-grids terms-grids'>
	<table cellspacing='10'  width="95%">
	<tr><th>Airline Company</th><th>Flight</th><th>Date </th><th>Price</th><th>Action</th></tr>
	<%List<Flight> list = order.getItems();
	double total  = 0;
	for(int i = 0; i < list.size(); i++){%>
		<tr>
			
			<td><%=list.get(i).getCarrier() %></td>
			
			<td><%=list.get(i).getOrigin() %> - <%=list.get(i).getDestination() %></td>
			
			<td><%=list.get(i).getDate() %></td>
			<td><%=list.get(i).getPrice() %></td>
			<td><a href='Cart.jsp?del=<%=i%>'>Delete</a></td></tr>
		
	<% total += list.get(i).getPrice();
	}%>
	<tr><td>Total Price: </td><td></td><td></td><td><%=total %><td></tr>
	<tr><td colspan='4'><a class ="send" href='Checkout.jsp'>Checkout</a></td></tr>
	</table>
</div>
</div>
</div>
<%@ include file="Footer.jsp" %>