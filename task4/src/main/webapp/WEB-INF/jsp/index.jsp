<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:choose>
	<c:when test="${not empty currentUser and currentUser.type.toString()=='ADMIN'}">
		<c:set var="isAdmin" value="true" />
	</c:when>
	<c:otherwise>
		<c:set var="isAdmin" value="false" />
	</c:otherwise>
</c:choose> 
<head>
<meta charset="UTF-8">
<title>Список Абитуиентoв</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
	<div class="wraper">
		<h4><c:if test="${not empty currentUser}">${currentUser.login}</c:if></h4>
		<h1>Абитуиенты</h1>
		<h2>Список</h2>
		<table>
			<tr>
				<th>Имя</th>
				<th>Фамилия</th>
				<th>Результат</th>
				<td>&nbsp;</td>
			</tr>
			<c:forEach var="applicant" items="${aplicants}">
				<tr>
					<td class="content"><c:out value="${applicant.name}"></c:out></td>
					<td class="content">${applicant.surname}</td>
					<td class="content">${applicant.sertificate}</td>
					<td class="empty"><c:if test="${isAdmin}"><a href="<%=request.getContextPath()%>/applicant.html?applicant_id=${applicant.id}" class="edit">more</a></c:if></td>
					<td class="empty"><c:if test="${isAdmin}"><a href="<%=request.getContextPath()%>/applicant/edit.html?user_id=${applicant.id}" class="edit">edit</a></c:if></td>
				</tr>
			</c:forEach>
		</table>

		<a href="index.html" class="add-button">All</a> <a
			href="facultylist.html" class="add-button">Facultys</a>
		<c:forEach var="exam" items="${exams}">
			<a href="list.html?id=${exam.id}">${exam.name}</a>
		</c:forEach>
		<br>
		<c:if test="${empty exams}">
			<a href="selected.html?id=<%=request.getParameter("id")%>">Selected</a>
		</c:if>
		<c:if  test="${isAdmin}"><a href="user/list.html">users</a></c:if>
		<c:if  test="${not empty currentUser and not isAdmin}"><a href="<%=request.getContextPath()%>/applicant.html?applicant_id=${currentUser.id}">Home
		</a></c:if>
		<a href="login.html">login</a>
		<a href="logout.html">logout</a>


	</div>
</body>
</html>