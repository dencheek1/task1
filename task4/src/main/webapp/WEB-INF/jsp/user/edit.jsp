<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:choose>
	<c:when test="${isNew}"><c:set var="url" value="/user/create.html" /></c:when>
	<c:otherwise><c:set var="url" value="/user/update.html" /></c:otherwise>
</c:choose> 
<head>
<meta charset="UTF-8">
<link href="../style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="wraper">
	
	    <c:if test="${not empty param.message}">
        		<p class="error">${param.message}</p>
    	</c:if>
		<c:if test="${ not empty currentUser }">
			<h2>Current user ${currentUser.type}</h2>
		</c:if>
		<h2>Edited user ${user.login }</h2>
		<form action="<%=request.getContextPath()%>${url}" method="post">

			<input name="user_id" value="${user.id}"
				type="hidden"> 
				<input name="isNew" value="${isNew}"type="hidden"> 
				<label for="login">New login:</label> 
				<input name="login" value="${user.login}">
				<label for="newPassword">New password:</label>
				<input id="password" name="newPassword" type="password">
				<c:if test="${not isNew and not empty currentUser and currentUser.type.toString() != 'ADMIN' }">
					<label for="oldPassword">old Password:</label>
					<input id="password" name="oldPassword" type="password"> 
				</c:if>
        		<c:if test="${not empty currentUser and currentUser.type.toString()== 'ADMIN' }">
				<select name="userType">
				<c:forEach var="type" items="${types}">
					<c:choose>
						<c:when test="${user.type == type }">
							<option selected="selected" value="${type}">${type}</option>
						</c:when>
						<c:otherwise>
							<option value="${type}">${type}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				</c:if>

			</select>
			<button class="save">Сохранить</button>
			<c:if test="${not isNew}"><button class="delete" formaction="<%=request.getContextPath()%>/user/delete.html?"
				formmethod="post" ${disabled}>Удалить</button>
			</c:if>
			<a class="back" href="<%=request.getContextPath()%>/index.html">Отменить</a>
		</form>

	</div>
</body>
</html>