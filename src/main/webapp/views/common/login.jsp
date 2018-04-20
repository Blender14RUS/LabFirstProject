<c:url value="/login" var="loginUrl"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>
    <link rel="icon" href="../../resources/logo.png">

    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<jsp:include page="../layout/_menu.jsp"></jsp:include>
<body class="text-center">
<c:url value="/login" var="loginUrl"/>
<div class="wrapper">
<form form class="form-signin" action="/login" method="post">
    <img class="mb-4" src="../../resources/static/logo.png" height="200">
    <h1 class="h3 mb-3 font-weight-normal">LOGIN</h1>
    <p>
        <label for="username" class="sr-only">Username</label>
        <input type="text" id="username" class="form-control" name="username"/>
    </p>
    <p>
        <label for="password" class="sr-only">Password</label>
        <input type="password" id="password" class="form-control" name="password"/>
    </p>
        <button class="btn btn-lg btn-block btn-success" type="submit" class="btn">Log in</button>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <br>
    <c:if test="${param.error != null}">
        <p>
            Invalid username and password.
        </p>
    </c:if>
    <c:if test="${param.logout != null}">
        <p>
            You have been logged out.
        </p>
    </c:if>
    <br>
    <p class="mt-5 mb-3 text-muted">EPAM 2018</p>

</form>
</div>
</body>
</html>
