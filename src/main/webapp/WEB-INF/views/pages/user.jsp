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
	<form:form modelAttribute="user" action="${pageContext.request.contextPath}/user/save" method="GET">
		<div>
			<label>Name</label>
			<form:input type="text" id="firstName" path="firstName" />
		</div>
		<div>
			<label>Last Name</label>
			<form:input path="lastName" />
		</div>
		<div>
			<label>CPF</label>
			<form:input path="cpf" />
		</div>
		<div>
			<button type="submit">Salvar</button>		
		</div>
	</form:form>
	
	<h2>List of Users</h2>
	<table>
		<tr>
			<td>Name</td>
			<td>Last Name</td>
			<td>CPF</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.cpf}</td>
			</tr>
		</c:forEach>
	</table>
	
</body>
</html>