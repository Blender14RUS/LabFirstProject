<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="wrapper">
<form class="form-signin" action="/registration" method="POST">
    <img class="mb-4" src="../../resources/static/logo.png" height="200">
    <h1 class="h3 mb-3 font-weight-normal">LOGIN</h1>
    <c:if test="${error-create != '2'}">
        <p>
            User with this name already exists.
        </p>
    </c:if>
    <input name="login" placeholder="Login" class="form-control" value="${user.login}">
    <br>
    <input name="name" placeholder="Name" class="form-control" value="${user.name}">
    <br>
    <c:if test="${error-password != '2'}">
        <p>
            Passwords don't match.
        </p>
    </c:if>
    <input type="password" name="password"  id = txtPassword" class="form-control" placeholder="Password" >
    <input type="password" name="confirm-password" id = txtConfirmPassword" class="form-control" placeholder="Confirm Password">
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
</form>
</div>
</body>
</html>
