<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Form</title>
</head>
<body>
	<h2>User Form</h2>
	<a href="${pageContext.request.contextPath}/user?myLocale=en">English</a> |
	<a href="${pageContext.request.contextPath}/user?myLocale=pt_BR">Huehuehue</a>
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
	
	
	
	<sec:authorize access="hasRole('ADMIN')">
	<div class="container">
	<form:form class="form col-sm-6" modelAttribute="user" action="${formAction}" method="${formMethod}">
		<div class="form-group row">
			<label><spring:message code="user.firstName" />:</label>
			<form:input class="form-control" type="text" id="firstName" path="firstName" />
		</div>
		<div class="form-group row">
			<label><spring:message code="user.lastName" /></label>
			<form:input class="form-control" path="lastName" />
		</div>
		<div class="form-group row">
			<label><spring:message code="user.cpf" /></label>
			<form:input class="form-control" path="cpf" />
		</div>
		
		<div class="form-group row">
				<label><spring:message code="user.dateBirth" /></label>
				<form:input class="form-control" path="dateBirth" />
		</div>
		
		<div class="form-group row">
			<label><spring:message code="user.username" /></label>
			<form:input class="form-control" path="username" />
		</div>
		
		<div class="form-group row">
			<label><spring:message code="user.password" /></label>
			<form:input type="password" class="form-control" path="password" />
		</div>
		
		<div class="form-group row">
			<label for="userProfile" ><spring:message code="user.role" /></label>
			<form:select id="userProfile" path="userProfiles" items="${profiles}" multiple="true"
					itemValue="id" itemLabel="type" class="form-control input-sm" />
		</div>
		
		<div class="form-group row">
			<button class="btn btn-primary" type="submit">
				<i class="fa fa-floppy-o" aria-hidden="true"></i> Salvar
			</button>		
		</div>
	</form:form>
	</div>
	</sec:authorize>
	
	
	
	<h2>List of Users</h2>
	
	<form class="form-inline" action="${pageContext.request.contextPath}/user/find-by/">
		<div class="input-group">
			<input type="text" class="form-control" name="lastName" 
			placeholder="Digite um sobrenome" />
		</div>
		<div class="input-group">
			<input type="text" class="form-control" name="firstName" 
			placeholder="Digite um nome" />
		</div>
		<div class="input-group">
			<input type="text" class="form-control" name="cpf" 
			placeholder="Digite um cpf" />
		</div>
		<button type="submit" class="btn btn-primary" >Pesquisar</button>
	</form>
	
	<table class="table table-striped">
		<tr>
			<td>Name</td>
			<td>Last Name</td>
			<td>CPF</td>
			<td>Edit</td>
			<td>Delete</td>
			
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.firstName}</td>
				<td>${user.lastName}</td>
				<td>${user.cpf}</td>
				<td><a href="${pageContext.request.contextPath}/user/${user.id}">Edit</a></td>
				<td>
					<a href="#myModal_${user.id}" role="button"
						class="btn btn-danger"
						data-toggle="modal"
					>Delete <i class="fa fa-trash-o"></i></a>
				</td>
			</tr>
			<div id="myModal_${user.id}" class="modal fade">
				<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title">Confirm Delete</h4>
					</div>
					<div class="modal-body">
			                <p>Are you sure you want to delete this user? </p>
			        </div>
		            <div class="modal-footer">
		                <button class="btn btn-default" data-dismiss="modal">Close</button>
						<a href="${pageContext.request.contextPath}/user/delete/${user.id}"
						class="btn btn-danger"
					>Delete</a>
		            </div>
				</div>
				</div>
			</div>
		</c:forEach>
		
		
		
		
		
		
		
		
	</table>
</body>
</html>