<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:choose>
	<c:when test="${isNew}">
		<c:set var="title" value="Редактирование данных" />
	</c:when>
	<c:otherwise>
		<c:set var="title" value="Добавление нового" />
	</c:otherwise>
</c:choose>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet">
<title>${title}</title>
</head>
<body>
	<div class="wraper">
		<h2>Requirement</h2>
		<form action="requirementsave.html" method="post">

			<input name="faculty_id" value="${faculty.id}" type="hidden">
			<input name="oldExam" value="${oldExam}" type="hidden">
			<input name="isNew" value="${isNew}" type="hidden"> <label
				for="group">Group:</label> <input name="group" value="${req.group}">
			<label for="value">Min value:</label> <input name="value"
				type="number" max="100" min="0" value="${req.value }"> <label
				for="exam">Exam:</label> <select name="exam">
				<c:forEach var="examination" items="${exams}">
					<c:choose>
						<c:when test="${examination == req.exam }">
						<option selected="selected" value="${examination}">${examination.name}</option> 
						</c:when>
						<c:otherwise>
						<option value="${examination}">${examination.name}</option> 
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</select>
			<button class="save">Сохранить</button>
			<button class="delete" formaction="requirementdelete.html"
				formmethod="post" ${disabled}>Удалить</button>

			<a class="back" href="index.html">Отменить</a>
		</form>
	</div>
</body>
</html>