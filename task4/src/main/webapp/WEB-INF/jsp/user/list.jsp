<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="../style.css" rel="stylesheet" >

<title>Список пользователей</title>
</head>
<body>
	<div class="wraper">
	
	    <c:if test="${not empty param.message}">
        		<p >"${param.message}"</p>
    	</c:if>
		<h3>${message }</h3>
		<h2>Список пользователей</h2>
		<table>
			<tr>
				<th>Логин</th>
				<th>Права</th>

			</tr>
			<c:forEach var="user" items="${users}">
				<tr>
					<td>${user.key.login}</td>
					<td>${user.key.type}</td>
					<td><c:if test="${not user.value}"><a class="button" href="<%=request.getContextPath() %>/applicant/add.html?user_id=${user.key.id}">registr applicant</a></c:if></td>
					<td><a class="button" href="<%=request.getContextPath() %>/user/edit.html?id=${user.key.id}">edit</a></td>
				</tr>
			</c:forEach>
		</table>
		<a class="button" href="<%=request.getContextPath()%>/index.html">All</a> <a class="button" href="<%=request.getContextPath() %>/user/add.html?id=0">Add</a>
	</div>
</body>
</html>