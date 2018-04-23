<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
<head>
    <title>
        <th><fmt:message key="userProfile.profile"/></th>
    </title>
</head>
<body class="blue-background">

<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="container">

    <div class="form-profile">

        <div class="row">
            <h4 class="col-sm-4 alert alert-info">${user.accessLevel}</h4>
        </div>
        <br class="divider">
        <div class="row">
            <div class="col-sm-1 blue"><h4><fmt:message key="login.Login"/>: </h4></div>
            <div class="col-sm-2"><h4>${user.login}</h4></div>
        </div>
        <br>
        <form action="/profile/change-name/${user.login}" method="POST">
            <div class="row">
                <div>
                    <label for="name" class="col-sm-1 blue"><h4><fmt:message key="adminBoard.name"/>:</h4></label>
                    <div class="col-sm-2">
                        <input id="name" name="name" class="form-control" value="${user.name}">
                    </div>
                    <button type="submit" class="btn button">
                        <span class="glyphicon glyphicon-pencil "></span>
                    </button>
                </div>
            </div>
        </form>
        <br>
    </div>
</div>
</fmt:bundle>
</div>
</body>
</html>