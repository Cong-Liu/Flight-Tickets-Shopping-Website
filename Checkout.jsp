<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="Header.jsp"%>

<div class="banner-bottom">
	<div class='container'>

		<div class="contact-grids">
			<%
				if (user == null)
					throw new Exception("Please login first before you can checkout.");
				session = request.getSession();
				if (session.getAttribute("cart") == null)
					throw new Exception("Your shopping cart is empty.");

				Order order = (Order) session.getAttribute("cart");
				if (order.getItems().size() == 0)
					throw new Exception("Your shopping cart is empty.");
				// get parameters from the request
				if (request.getMethod().equals("POST")) {

					//get user form
					String address = request.getParameter("address");
					String cardnum = request.getParameter("cardnumber");

					if (address == null || address.equals(""))
						throw new Exception("Please input your address.");
					if (cardnum == null || cardnum.equals(""))
						throw new Exception("Please input your credit card number.");

					//checkout the order
					order.checkout(address, cardnum);
					int orderNumber = Orders.GetInstance().add(order);
					String delivery = order.getDeliveryDateString();

					session.removeAttribute("cart");
			%>
			<h3>Your ticket has been placed!</h3>
			<h4>
				Ticket status tracking number:
				<%=orderNumber%></h4>
			<h4>
				Thanks for your shopping!</h4>

			<%
				} else {
			%>


			<div class="col-md-7 contact-para">
				<h5>Check Out</h5>
				<form method="post" action="Checkout.jsp">
					<div class="grid-contact">
						<div class="col-md-6 contact-grid">
							<p>Address</p>
							<input type="text" name="address">
						</div>
						<div class="col-md-6 contact-grid">
							<p>Card Number</p>
							<input type="text" name="cardnumber">
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="send">
						<input type="submit" value="Checkout">
					</div>
				</form>
			</div>

			<!-- 
	
      <h3>Checkout</h3>
        <fieldset>
            <form method="post" action="Checkout.jsp">
            <h4>Enter your Address and Credit card number</h4>
                <table cellpadding='2' cellspacing='1'  width="95%">
                    <tr>
                        <td>Address</td>
                        <td><input type="TEXT" size="15" name="address" style="color:white"></input></td>
                    </tr>
                    <tr>
                        <td>Card Number</td>
                        <td><input type="TEXT" size="15" name="cardnumber" style="color:white"></td>
                    </tr>
                    <tr>
                    	<td colspan='2' align="center">
                            <input type="submit" value="Checkout" class="formbutton"/>
                    	</td>
                    </tr>
                </table>
            </form>
        </fieldset>
        </div></div></div> -->
			<%
				}
			%>
		</div>
	</div>
</div>
<%@ include file="Footer.jsp"%>