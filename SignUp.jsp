<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ include file="Header.jsp" %>

<!-- banner-bottom -->
	<div class="banner-bottom">
		<!-- container -->
		<div class="container">
			<div class="faqs-top-grids">
				<div class="book-grids">
					<div class="col-md-6 book-left">
						<div class="book-left-info">
							<h3>Create Your BestFlight Account</h3>
						</div>
						<div class="book-left-form">
							<form method="post" action="SignUp">
								<p>Username</p>
								<input type="text" name="username" value="">
								<p>Password</p>
								<input type="password" name="password1" id="password">
								<p>Confirm Password</p>
								<input type="password" name="password2" id="password">
								<input type="submit" id="login" value="Register">
							</form>
						</div>
					</div>
					<div class="col-md-6 book-left book-right">
						<div class="book-left-info">
							<h3>Recommended</h3>
						</div>
						<ul>
							<li>Access booking history with upcoming trips</li>
							<li>Print tickets and invoices</li>
							<li>Make checkouts simpler</li>
							<li>Enter your contact details only once</li>
							<li>Get alerts for low fares</li>
						</ul>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- //container -->
	</div>
	<!-- //banner-bottom -->
	
<%@ include file="Footer.jsp" %>