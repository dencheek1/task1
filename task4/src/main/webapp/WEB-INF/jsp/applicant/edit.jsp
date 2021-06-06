<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:choose>
	<c:when test="${isNew}"><c:set var="url" value="/applicant/registration.html" /></c:when>
	<c:otherwise><c:set var="url" value="/applicant/update.html" /></c:otherwise>
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
		<h2>Applicant </h2>
		<form action="<%=request.getContextPath()%>${url}" method="post">

			<input name="applicant_id" value="${applicant.id}"
				type="hidden"> 
				<input name="isNew" value="${isNew}"type="hidden"> 
				<label for="name">Name:</label> 
				<input name="name" value="${applicant.name}">
				<label for="surname">Surname:</label> 
				<input name="surname" value="${applicant.surname}">
				<label for="sertificate">Sertificate value:</label> 
				<input max="100" min="0" name="sertificate" value="${applicant.sertificate}">

				<select name="faculty">
				<c:forEach var="faculty" items="${facultys}">
					<c:choose>
						<c:when test="${not empty applicant and applicant.facultyId == faculty.id }">
							<option selected="selected" value="${faculty.id}">${faculty.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${faculty.id}">${faculty.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</select>
			<button class="save">Сохранить</button>
			<c:if test="${not isNew}"><button class="delete" formaction="<%=request.getContextPath()%>/applicant/delete.html"
				formmethod="post" ${disabled}>Удалить</button>
			</c:if>
			<a class="back" href="<%=request.getContextPath()%>/index.html">Отменить</a>
		</form>

	</div>
</body>
</html>