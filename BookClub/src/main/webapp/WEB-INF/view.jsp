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
	<h1>${book.title}</h1>
	<a href="/books" class="btn btn-warning">Go Back</a>
	<c:choose> 
		<c:when test="${book.user.id == user.id}">
			<h2> <span style="color:red" >You</span> read <span style="color:green">${book.title }</span> by <span style="color:blue">${book.author}</span>. </p>
		<h4>Here are your thoughts:</h2>
		</c:when>
		<c:when test="${ book.user.id != user.id}">
			<h2> <span style="color:red" >${book.user.name}</span> read <span style="color:green">${book.title }</span> by <span style="color:blue">${book.author}</span>. </h2>
		<h4>Here are their thoughts:</h4>
		</c:when>
	</c:choose>
	<p> ${book.thoughts}</p>
	<c:if test="${book.user.id == user.id}">
	<div style="display: flex; flex-direction: row;">
		<a href="/books/${book.id}/edit" class="btn btn-warning">Edit</a>
		<form:form action="/books/${book.id}" method="post">
		    <input type="hidden" name="_method" value="delete">
		    <input type="submit" value="Delete" class="btn btn-danger" >
		</form:form>
	</div>
	</c:if>
	</body>
</html>