<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" >
</head>
<body>

<div class="wraper">
	<h2>${faculty.name}</h2>
	<table>
	<c:forEach var="req" items="${requirements}">
	<tr>
	<th>${req.exam}</th>
	<th>${req.value}</th>
	<th>${req.group}</th>

	<th><a href="requirementedit.html?exam=${req.exam}&facultyid=${faculty.id}" >edit</a></th>
	</tr>
	</c:forEach>
	</table>
	<a href="list.html?id=${faculty.id}" >faculty</a>
	<a href="index.html">All</a>
	<a href="requirementedit.html?isNew=true&facultyid=${faculty.id}" >Add</a>

</div>
</body>
</html>