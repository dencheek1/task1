<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<c:choose>
	<c:when test="${isNew}"><c:set var="url" value="/applicant/result/create.html" /></c:when>
	<c:otherwise><c:set var="url" value="/applicant/result/update.html" /></c:otherwise>
</c:choose> 
<head>
<meta charset="UTF-8">
<link href="../style.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>
	<div class="wraper">
		<h2>Абитуриент</h2>
		<form action="<%=request.getContextPath()%>${url}" method="post">

			<input name="applicant_id" value="${result.aplicant.id}"
				type="hidden"> 
				<input name="oldExam" value="${oldExam}" type="hidden">
				<input name="isNew" value="${isNew}"type="hidden"> 
				<label for="result">Result:</label> 
				<input max="100" min="0" name="result" value="${result.result}"> 
				<label for="checked">Checked:</label> <input name="checked" type="checkbox" >
				<label for="exam">Exam:</label>
			<select name="exam">
				<c:forEach var="examination" items="${exams}">
					<c:choose>
						<c:when test="${examination == result.exam }">
							<option selected="selected" value="${examination}">${examination}</option>
						</c:when>
						<c:otherwise>
							<option value="${examination}">${examination.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>

			</select>
			<button class="save">Сохранить</button>
			<c:if test="${not isNew }"><button class="delete" formaction="<%=request.getContextPath()%>/applicant/result/delete.html"
				formmethod="post" ${disabled}>Удалить</button></c:if>

			<a class="back" href="<%=request.getContextPath()%>/applicant.html">Отменить</a>
		</form>

	</div>
</body>
</html>