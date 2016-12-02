<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="Header.jsp" %>
<!-- import packages and classes needed by the scripts -->
<%@ page import="java.util.List,database.*" %>
<jsp:useBean class="models.Order" id="order" scope="request"/>
<jsp:setProperty name="order" property="*"/>
		<div class = "banner-bottom">
	<div class='container'>
	<div class = 'faqs-top-grids book-grids'>
<%       
	if (user == null) throw new Exception("Please login before you can access your orders.");
	
	List<Order> list = Orders.GetInstance().getList(user.getName());
	if (list == null || list.size() == 0) throw new Exception("Your do not have any orders yet.");
	
	// check if user is adding a new item to cart
	String id = request.getParameter("cancel");
	if (id != null) {
		int idx = Integer.parseInt(id);
		if (idx < 0)
			throw new Exception("Illegal order index.");
		if(!Orders.GetInstance().findById(idx).cancel())
			throw new Exception("Your ticket is purchersed by one day, unable to cancel your order.");
		try {			
			MySQLDataStoreUtilities.deleteOrder(idx);
			Orders.GetInstance().delete(idx);
			list=Orders.GetInstance().getList(user.getName());
		} catch (Throwable e) {
			throw new Exception("Cannot delect this order in database"+e.getMessage());
		}
	}%>

	<table cellspacing='0'  width="95%">
	<tr><th>Order Number</th><th>Price</th><th>Order Date</th><th>Status</th><th>Action</th></tr>
	<%
	for (int i = 0; i < list.size(); i++) {
		order = list.get(i);%>
		<tr>
			<td><%=order.getOrderNumber()%></td>
			<td><%=order.getTotalPrice()%></td>
			<td><%=order.getDate()%></td>
			<td><%=order.getStatusString() %></td>
			<td><a href='MyOrder.jsp?cancel=<%=order.getOrderNumber()%>'>Cancel</a></td>
		</tr>
		
	<%}%>
	</table>
	</div>
	</div>
	</div>
	
<%@ include file="Footer.jsp" %>