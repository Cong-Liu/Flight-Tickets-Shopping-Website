<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="Header.jsp"%>

<!-- banner -->
<div class="banner">
	<!-- container -->
	<div class="container">
		<div class="col-md-4 banner-left">
			<section class="slider2">
				<div class="flexslider">
					<div class="flex-viewport"
						style="overflow: hidden; position: relative">
						<ul class="slides">
							<li style="width: 350px; float: left; display: block">
								<div class="slider-info">
									<img src="images/2.jpg" alt="">
								</div>
							</li>
						</ul>
					</div>
				</div>
			</section>
		</div>
		<div class="col-md-8 banner-right">
			<div class="sap_tabs">
				<div class="booking-info">
					<h2>Book Domestic &amp; International Flight Tickets</h2>
				</div>

				<div id="horizontalTab"
					style="display: block; width: 100%; margin: 0px;">
					<div class="resp-tabs-container">
						<div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1"
							style="display: block">
							<div class="facts">
								<div class="booking-form">
									<div class="online_reservation">
										<div class="b_room">
											<form class="booking_room" method="post" action='List.jsp'>
												<div class="reservation">
													<ul>
														<li class="span1_of_1 desti">
															<h5>Flying from</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-map-marker"
																	aria-hidden="true"></span> <input type="text"
																	placeholder="Type Departure City"
																	class="typeahead1 input-md form-control tt-input"
																	name='origin'>
															</div>
														</li>
														<li class="span1_of_1 left desti">
															<h5>Flying to</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-map-marker"
																	aria-hidden="true"></span> <input type="text"
																	placeholder="Type Destination City"
																	class="typeahead1 input-md form-control tt-input"
																	name='destination'>
															</div>
														</li>
													</ul>
												</div>
												<div class="reservation">
													<ul>
														<li class="span1_of_1">
															<h5>Departure</h5>
															<div class="book_date">
																<span class="glyphicon glyphicon-calendar"
																	aria-hidden="true"></span> <input type="date"
																	name='date' value="Select date"
																	onFocus="this.value = '';"
																	onBlur="if (this.value == '') {this.value = 'Select date';}">
															</div>
														</li>
														<!-- <li class="span1_of_1 left">
																		 <h5>Passengers</h5>
																		 
																		 <div class="section_room">
																			  <select id="country" onChange="change_country(this.value)" class="frm-field required" name='passengers'>
																					<option value="1">1</option>
																					<option value="2">2</option>         
																					<option value="3">3</option>
																					<option value="4">4</option>
																					<option value="5">5</option>
																					<option value="6">6</option>
																			  </select>
																		 </div>	
																	</li>  -->
													</ul>
												</div>
												<div class="reservation">
													<ul>
														<li class="span1_of_3">
															<div class="date_btn">
																<input type="submit" value="Search" class="date_btn" />
															</div>
														</li>											
													</ul>
												</div>
											</form>
											<div class="clearfix"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!-- //container -->
</div>
<!-- //banner -->

<%@ include file="Footer.jsp"%>