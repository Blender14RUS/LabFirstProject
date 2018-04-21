<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>?
<html>
<head>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
    <title><fmt:message  key="registration.registration"/>Registration</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</fmt:bundle>
</head>
<body>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="wrapper">
<form class="form-signin" action="/registration" method="POST">
    <img class="mb-4" src="../../resources/static/logo.png" height="200">
    <h1 class="h3 mb-3 font-weight-normal">  <fmt:message  key="login.LOGIN"/>LOGIN</h1>
    <c:if test="${error-create != '2'}">
        <p>
            <fmt:message  key="registration.userWithThisNameAlreadyExists"/> User with this name already exists.
        </p>
    </c:if>
    <input name="login" placeholder="  <fmt:message  key="login.Login"/>Login" class="form-control" value="${user.login}">
    <br>
    <input name="name" placeholder=  <fmt:message  key="adminBoard.name"/>"Name" class="form-control" value="${user.name}">
    <br>
    <c:if test="${error-password != '2'}">
        <p>
            <fmt:message  key="registration.passwordDontMatch"/>Passwords don't match.
        </p>
    </c:if>
    <input type="password" name="password"  id = txtPassword" class="form-control" placeholder=  <fmt:message  key="login.password"/>"Password" >
    <input type="password" name="confirm-password" id = txtConfirmPassword" class="form-control" placeholder="  <fmt:message  key="registration.confirmPassword"/>Confirm Password">
    <br>
    <button class="btn btn-lg btn-primary btn-block" type="submit">  <fmt:message  key="registration.register"/>Register</button>
</form>
</div>
</fmt:bundle>
</body>
</html>
