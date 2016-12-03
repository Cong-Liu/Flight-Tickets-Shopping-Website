<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" isErrorPage="true"%>
<%@ include file="Header.jsp"%>
<div class="banner-bottom">
	<div class='container'>
		<div class='faqs-top-grids terms-grids'>
			<div class="move-text">
				<div class="marquee">
					<%
						if(exception.getCause() != null && !exception.getCause().getMessage().isEmpty()) out.println(exception.getCause().getMessage());
						else out.println(exception.getMessage());
					%>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="Footer.jsp"%>