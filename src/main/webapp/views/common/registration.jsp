<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../common/_menu.jsp"></jsp:include>

${message}

<form action="/registration" method="POST">
    <input name="login" placeholder="Login" value="${user.login}">
    <br>
    <input name="name" placeholder="Name" value="${user.name}">
    <br>
    <input type="password" id="pass" placeholder="Password" >
    <input type="password" id="confirm-pass" placeholder="Confirm Password">
    <br>
    <input type="submit" value="Create">
</form>

</body>
</html>