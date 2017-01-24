<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="Header.jsp"%>
<%@ page import="java.util.List,database.*,java.util.ArrayList"%>
<jsp:useBean class="models.Flight" id="Flight" scope="request" />
<!-- banner-bottom -->
<div class="banner-bottom">
	<!-- container -->
	<div class="container">
		<div class="faqs-top-grids">
			<div class="product-grids">
				<div class="col-md-3 product-left">
					<div class="h-class p-day">
						<h5>Stops</h5>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								checked="checked" disabled="disabled"> <span
								class="p-day-grid"
								title="Currently only support none-stop flights">Nonestop</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								disabled="disabled"> <span class="p-day-grid"
								title="Currently only support none-stop flights">1 Stop</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								disabled="disabled"> <span class="p-day-grid"
								title="Currently only support none-stop flights">2+ Stops</span>
							</label>
						</div>
					</div>
					<div class="h-class p-day">
						<h5>Airlines Included</h5>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="UA"> <span class="p-day-grid">United</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="AA"> <span class="p-day-grid">American
									Airlines</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="DL"> <span class="p-day-grid">Delta</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="NK"> <span class="p-day-grid">Spirit
									Airlines</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="F9"> <span class="p-day-grid">Frontier
									Airlines</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"
								name="chkAirline" value="VX"> <span class="p-day-grid">Virgin
									America</span>
							</label>
						</div>
					</div>
					<div class="h-class p-day">
						<h5>Departure Time</h5>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="p-day-grid">Early Morning</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="p-day-grid">Morning</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="p-day-grid">Afternoon</span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="p-day-grid">Evening</span>
							</label>
						</div>
					</div>
					<div class="h-class">
						<h5
							title="Flight score reflects the duration of the flight the type of aircraft, and the quality of amenities the flight offers.">Rating</h5>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							</label>
						</div>
						<div class="hotel-price">
							<label class="check"> <input type="checkbox"> <span
								class="glyphicon glyphicon-star" aria-hidden="true"></span>
							</label>
						</div>
					</div>

				</div>
				<div class="col-md-9 product-right">
				<%	Flights.GetInstance().getlist();
					List<Flight> flist = null;
					
					if (request.getMethod().equals("POST")) {
						String or = request.getParameter("origin");
						String des = request.getParameter("destination");
						String d = request.getParameter("date");
						flist = MySQLDataStoreUtilities.getFlights(or, des, d);
						session.setAttribute("flights", flist);
						if (flist.isEmpty()) throw new ServletException("<h5>No flight founded! Please search again!</h5><script>setTimeout(function(){location.href='Home.jsp'}, 2000);</script>");
					}
					else {
						
						List<Flight> list = (List<Flight>)request.getSession().getAttribute("flights");
						if(list == null || list.isEmpty()) throw new ServletException("<h5>Please search flight first.</h5><script>setTimeout(function(){location.href='Home.jsp'}, 2000);</script>");
						
						String carr = request.getParameter("airline");
						if(carr == null || carr.equals("")) flist = list;
						else {
							//filter flight list by airline
							flist = new ArrayList<Flight>();
							for (Flight f : list) {
								if (f.getShortC().equalsIgnoreCase(carr))
									flist.add(f);
							}
						}
					}			
					
					for (Flight f : flist) {
						String deptime = f.getDepartureTime();
						String arrtime = f.getArrivalTime();
						String t = deptime + " - " + arrtime;
						String addr = f.getOrigin().toUpperCase() + " - " + f.getDestination().toUpperCase();
					%>
							<div class="product-right-grids">
								<div class="product-right-top">
									<div class="p-left">
										<div class="p-right-img">
											<a href="#"
												style="background-image: url('images/Airlines/<%=f.getFlightNumber().substring(0, 2)%>.jpg')"></a>
										</div>
									</div>
									<div class="p-right">
										<div class="col-md-6 p-right-left">
											<a><%=f.getFlightNumber()%></a>
											<p><%=f.getCarrier()%></p>
											<p class="p-call"><%=t%></p>
										</div>
										<div class="col-md-6 p-right-right">
											<h6>Rating : 4.1/5</h6>
											<p><%=addr%></p>
											<span class="p-offer"><strong>Price: $ <%=String.format("%.2f", f.getPrice()) %></strong></span>
											<p>
												<a class="best-btn" href="Cart.jsp?add=<%=f.getId()%>">Select</a>
											</p>
										</div>
										<div class="clearfix"></div>
									</div>
									<div class="clearfix"></div>
								</div>
							</div>
					<% } %>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<!-- //container -->
</div>
<!-- //banner-bottom -->

<script type="text/javascript">
	//select filtered airline
	if (location.search.indexOf("?airline=") >= 0) {
		var code = location.search.substr(9);
		$("input[name='chkAirline'][value='" + code + "']").attr("checked", true);
	}

	//filter search result by airline
	$("input[name='chkAirline']").click(function() {
		if (this.checked)
			location.href = "?airline=" + this.value;
		else
			location.href = "?";
	});
</script>

<%@ include file="Footer.jsp"%>