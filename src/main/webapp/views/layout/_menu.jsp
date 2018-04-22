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
    <link rel="stylesheet" href="../../resources/font-awesome/css/fontawesome-all.css">
</head>

<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">LibApp</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/books"><i class="fas fa-book fa-fw" style="color:white"></i> <fmt:message  key="bookList.catalog"/></a></li>

                <security:authorize access="hasRole('ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/board"><fmt:message  key="menu.adminBoard"/> </a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li><a href="${pageContext.request.contextPath}/user/orders"><i class="far fa-shopping-cart" style="color: white"></i> <fmt:message  key="menu.myOrders"/></a></li>
                </security:authorize>
                <security:authorize access="hasAnyRole('ADMIN','LIBRARIAN')">
                    <li><a href="${pageContext.request.contextPath}/lib/requested-books"><i class="far fa-clipboard-list" style="color: white"></i> <fmt:message  key="menu.requestedBooks"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/lib/returned-books"><i class="far fa-hand-receiving" style="color: white"></i> <fmt:message  key="menu.returnedBooks"/></a></li>
                    <li><a href="${pageContext.request.contextPath}/lib/addBook"><i class="fal fa-book" style="color: white"></i> <fmt:message  key="menu.addBook"/></a></li>
                </security:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <security:authorize access="!isAuthenticated()">
                    <li><a class="btn btn-lg " href="${pageContext.request.contextPath}/login"><i class="far fa-sign-in-alt" style="color: white"></i> <fmt:message key="login.Log_in"/></a></li>
                    <li><a class="btn btn-clear " href="${pageContext.request.contextPath}/registration"><fmt:message  key="menu.register"/></a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fas fa-user" style="color: white"></i>
                            <security:authentication property="principal.username"/>
                            <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="/profile"><i class="far fa-cog" style="color: black"></i> <fmt:message  key="menu.editProfile"/></a></li>
                            <li class="divider"></li>
                            <li><a href="/auth/logout"><i class="far fa-sign-out-alt" style="color: black"></i> <fmt:message  key="menu.logOut"/></a></li>
                        </ul>
                    </li>
                </security:authorize>
                <li>
                    <form action="${self}" method="POST">
                        <input name="lang_changed" value="true" hidden/>
                        <select name="lang" onchange="submit()">
                            <option value="en_US"> EN </option>
                            <option value="ru_RU" <c:if test= "${language == 'ru_RU'}"> selected </c:if>> RU </option>
                        </select>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    </div>
</fmt:bundle>
</body>
</html>
