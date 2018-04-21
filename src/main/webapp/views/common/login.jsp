<c:url value="/login" var="loginUrl"/>
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


<body class="text-center">
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
<c:url value="/login" var="loginUrl"/>
<div class="wrapper">
    <form form class="form-signin" action="/login" method="post">
        <img class="mb-4" src="../../resources/static/logo.png" height="200">
        <h1 class="h3 mb-3 font-weight-normal"><fmt:message key="login.LOGIN"/></h1>
        <p>
            <label for="username" class="sr-only"><fmt:message key="login.username"/></label>
            <input type="text" id="username" class="form-control" name="username"/>
        </p>
        <p>
            <label for="password" class="sr-only"><fmt:message key="login.password"/></label>
            <input type="password" id="password" class="form-control" name="password"/>
        </p>
        <button class="btn btn-lg btn-block btn-success" type="submit" class="btn"><fmt:message
                key="login.Log_in"/></button>
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <br>
        <c:if test="${param.error != null}">
            <p>
                <fmt:message key="login.invalidUsernameAndPassword"/>
            </p>
        </c:if>
        <c:if test="${param.logout != null}">
            <p>
                <fmt:message key="login.youHaveBeenLoggedOut"/>
            </p>
        </c:if>
        <br>
        <p class="mt-5 mb-3 text-muted">EPAM 2018</p>

    </form>
</div>

</body>
</fmt:bundle>
</html>
