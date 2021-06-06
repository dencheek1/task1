<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" >
<title>Insert title here</title>
</head>
<body>
	<div class="wraper">
		<h2>факультет</h2>
		<form action="facultysave.html" method="post">
			<input name="id" value="${faculty.id}" type="hidden"> <label
				for="login">Факультет:</label> <input id="login" name="login"
				value="${faculty.name}"> <label for="count">Количество
				мест:</label> <input id="count" name="count" value="${faculty.count}">
			<button class="save">Сохранить</button>
			<button class="delete" formaction="facultydelete.html"
				formmethod="post" ${disabled}>Удалить</button>
			<a class="back" href="index.html">Отменить</a>
		</form>
	</div>
</body>
</html>