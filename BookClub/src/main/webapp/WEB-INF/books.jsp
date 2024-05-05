<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/style.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
	<div class="horizontal">
	 	<h1>Books go here! Welcome, ${user.name }! </h1>
	 	<a href="/logout" class="btn btn-danger">Logout</a>
	</div>
 	<div>
	 	<a href="/books/new" class="btn btn-success">+ Add Book</a>
	 	
 	</div>
 	<table class="table">
		 <tr>
		 	<th>ID</th>
		 	<th>Title</th>
		 	<th>Author</th>
		 	<th>Posted by:</th>
		 </tr>
		 <!-- book information -->
		 	<c:forEach var="book" items="${books}">
		 		<tr>
			 		<td>${book.id}</td>
			 		<td> <a href="/books/${book.id}">${book.title} </a> </td>
			 		<td>${book.author}</td>
			 		<c:choose>
			 			<c:when test="${book.user.name.equals(user.name)}">
			 				<td>Me</td>
			 			</c:when>
			 			<c:when test="${!book.user.name.equals(user.name)}">	 			
					 		<td>${book.user.name}</td>
			 			</c:when>
			 		</c:choose>
		 		</tr>
		 	</c:forEach>
 	</table>
</body>
</html>