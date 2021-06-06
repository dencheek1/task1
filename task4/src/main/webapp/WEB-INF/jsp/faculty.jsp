<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" >

<title>Список факультетов</title>
</head>
<body>
	<div class="wraper">
		<h2>Список факультетов</h2>
		<table>
			<tr>
				<th>Название</th>
				<th>Набор</th>
				<th></th>
				<th></th>
			</tr>
			<c:forEach var="fac" items="${faculty}">
				<tr>
					<td>${fac.name}</td>
					<td>${fac.count}</td>
					<td><a class="button" href="list.html?id=${fac.id}">faculty</a></td>
					<td><a class="button" href="requirements.html?id=${fac.id}">requirements</a></td>
					<td><a class="button" href="facultyedit.html?id=${fac.id}">edit</a></td>
				</tr>
			</c:forEach>
		</table>
		<a class="button" href="index.html">All</a> <a class="button" href="facultyedit.html?id=0">Add</a>
	</div>
</body>
</html>