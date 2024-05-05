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
	<div class="centered">
		<h1>Book Club</h1>
	</div>
	<div class="row align-iems-center">
		<div class="col padded">
			<form:form action="/register" method="post" modelAttribute="newUser">
				<div class="row g-3 align-items-center">
					<div class="col-auto">
						<form:label path="name" class="col-form-label" >Name: </form:label>
					</div>
					<div class="col-auto">
						<form:input path="name" class="form-control"/>
					</div>
				</div>
				<div>
					<form:label path="email" class="form-label" >Email: </form:label>
					<form:input path="email" class="form-control"/>
				</div>
				<div>
					<form:label path="password" class="form-label" >Password: </form:label>
					<form:input  type="password"  path="password" class="form-control"/>
				</div>
				<div>
					<form:label  path="confirm" class="form-label" >Confirm: </form:label>
					<form:input  type="password"  path="confirm" class="form-control"/>
				</div>
				<input type="submit" value="Submit" class="btn btn-success">
			</form:form>
			<c:forEach var="error" items="${registerErrors}">
				<p>${error.defaultMessage}</p>
			</c:forEach>
		</div>
		<div class="col padded">
	 		<form:form action="/login" method="post" modelAttribute="newLogin">
				
				<div>
					<form:label path="email" class="form-label" >Email: </form:label>
					<form:input path="email" class="form-control"/>
				</div>
				<div>
					<form:label path="password" class="form-label" >Password: </form:label>
					<form:input type="password" path="password" class="form-control"/>
				</div>
					<input type="submit" value="Submit" class="btn btn-success">
				
			</form:form> 
			<c:forEach var="error" items="${loginErrors}">
				<p>${error.defaultMessage}</p>
			</c:forEach>
		</div>
	</div>
</body>
</html>