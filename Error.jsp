<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isErrorPage="true" %>
<%@ include file="Header.jsp" %>

<div class="move-text">
	<div class="marquee">Error: <%=exception.getCause().getMessage() %></div>
</div>

<%@ include file="Footer.jsp" %>