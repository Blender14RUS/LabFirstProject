<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <link rel="stylesheet" href="../../resources/bootstrap/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <script src="../../resources/jquery/jquery-3.3.1.min.js"></script>
    <script src="../../resources/bootstrap/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>
</head>

<body>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">LibApp</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/books"><fmt:message key="bookList.catalog"/></a></li>

                <security:authorize access="hasRole('ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/board"><fmt:message
                            key="menu.adminBoard"/> </a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}/user/orders"><fmt:message key="menu.myOrders"/></a>
                    </li>
                </security:authorize>
                <security:authorize access="hasAnyRole('ADMIN','LIBRARIAN')">
                    <li><a href="${pageContext.request.contextPath}/lib/requested-books"><fmt:message
                            key="menu.requestedBooks"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/lib/returned-books"><fmt:message
                            key="menu.returnedBooks"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/lib/addBook"><fmt:message key="menu.addBook"/></a>
                    </li>
                </security:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="!isAuthenticated()">
                    <li><a class="btn btn-lg " href="${pageContext.request.contextPath}/login">Lod In</a></li>
                    <li><a class="btn btn-clear " href="${pageContext.request.contextPath}/registration"><fmt:message
                            key="menu.register"/></a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <span class="glyphicon glyphicon-user"></span>
                            <security:authentication property="principal.username"/>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile"><i class="icon-envelope"></i><fmt:message
                                    key="menu.editProfile"/></a></li>
                            <li class="divider"></li>
                            <li><a href="/auth/logout"><i class="icon-off"></i><fmt:message key="menu.logOut"/></a></li>
                        </ul>
                    </li>
                </security:authorize>


                <form action="${self}" method="POST">
                    <input name="lang_changed" value="true" hidden/>
                    <select name="lang" onchange="submit()">
                        <option value="en_US">EN</option>
                        <option value="ru_RU" <c:if test="${language == 'ru_RU'}">
                            selected
                        </c:if>
                        >RU
                        </option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>
    </div>
</fmt:bundle>
</body>
</html>

