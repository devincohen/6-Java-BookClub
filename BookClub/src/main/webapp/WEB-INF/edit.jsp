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
	<h1>Edit ${book.title}</h1>
	<form:form action="/books/${book.id}/edit" method="put" modelAttribute="book">
		<input type="hidden" name="_method" value="put">
		<form:input type="hidden" path="user" value="${user.id}"></form:input>
			<div>
			<form:label path="title" class="form-label">Title: </form:label>
			<form:errors class="error" path="title"></form:errors>
			<form:input path="title" value="${book.title}" class="form-control"/>
		</div>
		<div>
			<form:label path="author" class="form-label">Author: </form:label>
			<form:errors class="error" path="author"></form:errors>
			<form:input path="author" value="${book.author}" class="form-control"/>
		</div>
		<div>
			<form:label path="thoughts" class="form-label">My Thoughts: </form:label>
			<form:errors class="error" path="thoughts"></form:errors>
			<form:input path="thoughts" value="${book.thoughts}" class="form-control"/>
		</div>
		<div>
			<input type="submit" value="Save" class="btn btn-success">
		</div>
	</form:form>
</body>
</html>