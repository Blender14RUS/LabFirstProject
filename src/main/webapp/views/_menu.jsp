<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">LibApp</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="${pageContext.request.contextPath}/admin">Admin board </a></li>
            <li><a href="${pageContext.request.contextPath}/registration">Add user</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <span class="glyphicon glyphicon-user"></span>   ${user.name}<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="/profile"><i class="icon-envelope"></i> Edit profile</a></li>
                    <li class="divider"></li>
                    <li><a href="/auth/logout"><i class="icon-off"></i> Log out</a></li>
                </ul>
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">EN<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="/help/support"><i class="icon-envelope"></i> EN</a></li>
                    <li><a href="/auth/logout"><i class="icon-off"></i> RU</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</div>


</body>