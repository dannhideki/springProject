<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Form</title>
</head>
<body>
	<h2>User Form</h2>
	<c:choose>
		<c:when test="${user.id > 0}">
			<c:set var="formMethod" value="PUT"></c:set>
			<c:url var="formAction" value="/user/${user.id}"></c:url>
		</c:when>
		<c:otherwise>
			<c:set var="formMethod" value="POST"></c:set>
			<c:url var="formAction" value="/user"></c:url>
		</c:otherwise>
	</c:choose>
	
	<div class="container">
	<form:form class="form col-sm-6" modelAttribute="user" action="${formAction}" method="${formMethod}">
		<div class="form-group row">
			<label>Name</label>
			<form:input class="form-control" type="text" id="firstName" path="firstName" />
		</div>
		<div class="form-group row">
			<label>Last Name</label>
			<form:input class="form-control" path="lastName" />
		</div>
		<div class="form-group row">
			<label>CPF</label>
			<form:input class="form-control" path="cpf" />
		</div>
		<div class="form-group row">
			<button class="btn btn-primary" type="submit">
				<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
			</button>		
		</div>
	</form:form>
	</div>
	<h2>List of Users</h2>
	<table class="table table-striped">
		<tr>
			<td>Name</td>
			<td>Last Name</td>
			<td>CPF</td>
			<td>Edit</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.cpf}</td>
				<td><a href="${pageContext.request.contextPath}/user/${user.id}">Edit</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>