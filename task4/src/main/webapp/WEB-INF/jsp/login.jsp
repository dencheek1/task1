<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet">
</head>
<body>
<div class="wraper">
<h1>Login ${session} ${currentUser.login}</h1>
    <form action="" method="post">
        <label for="login">username</label>
        <input id="login" name="login">
        <label for="password">Password:</label>
        <input id="password" name="password" type="password">
        <button class="login">Login</button>
    </form>
</div>

</body>
</html>