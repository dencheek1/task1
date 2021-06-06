<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="style.css" rel="stylesheet" >

<title>applicant ${applicant.name}</title>
</head>
<body>
	<div class="wraper">
		<h4>${param.message}</h4>
		<h2>Абитуриент</h2>
		<h3>${applicant.name}</h3>
		<h3>${applicant.surname}</h3>
		<h3>${applicant.sertificate }</h3>
		<table>
			<tr>
				<th>Экзамен</th>
				<th>результат</th>
				<th>проверено</th>
			</tr>
			<c:forEach var="result" items="${results}">
				<tr>
					<td>${result.exam}</td>
					<td>${result.result}</td>
					<td>${result.checked}</td>
					<td><a class="button" href="<%=request.getContextPath()%>/applicant/resultedit.html?id=${applicant.id}&exam=${result.exam}">edit</a></td>
				</tr>
			</c:forEach>
		</table>
		<a class="button" href="<%=request.getContextPath()%>/index.html">All</a> <a class="button" href="<%=request.getContextPath()%>/applicant/resultadd.html">Add</a>
				<a class="button" href="<%=request.getContextPath()%>/applicant/edit.html?user_id=${applicant.id}">Applicant edit</a>
	</div>
</body>
</html>