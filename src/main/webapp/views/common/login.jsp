<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
<head>

    <link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>
    <link rel="icon" href="../../resources/logo.png">

    <title><fmt:message key="login.Login"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    </fmt:bundle>
    <jsp:include page="../layout/_menu.jsp"></jsp:include>
</head>


<body class="text-center blue-background">
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
<div class="wrapper">
    <form form class="form-signin" action="/login" method="post">
        <br>
        <img class="mb-4" src="../../resources/img/logo.png" height="200">
        <br>
        <h1 class="h3 mb-3 font-weight-normal blue"><fmt:message key="login.LOGIN"/></h1>
        <br>
        <p>
            <label for="username" class="sr-only"><fmt:message key="login.username"/> </label>
            <input type="text" id="username" class="form-control" placeholder=
                <fmt:message key="login.username"/> name="username"/>
        </p>
        <p>
            <label for="password" class="sr-only"><fmt:message key="login.password"/></label>
            <input type="password" id="password" class="form-control" placeholder=
                <fmt:message key="login.password"/> name="password"/>
        </p>
        <button class="btn btn-lg btn-block button" type="submit" class="btn"><fmt:message
                key="login.Log_in"/></button>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <br>
        <c:if test="${error eq true}">
            <p class="blue">
                <fmt:message key="login.invalidUsernameAndPassword"/>
            </p>
        </c:if>
        <br>
        <a href="/registration" class="blue mt-5 mb-3">Create an Account</a>
        <br>
        <br>
        <p class="mt-5 mb-3 text-muted">EPAM LAB 2018</p>
    </form>
</div>

</body>
</fmt:bundle>
</html>
