<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<script src="../../resources/jquery/jquery-3.3.1.min.js"></script>

<html>
<head>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
    <title><fmt:message  key="adminBoard.adminBoard"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</fmt:bundle>
</head>
<body>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="container">
    <h2><fmt:message  key="adminBoard.userList"/></h2>
    <p><fmt:message  key="adminBoard.setOrTakeAwayTheLibrarianRights"/></p>
    <table class="table table-striped ">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th>ID</th>
            <th><fmt:message  key="adminBoard.login"/></th>
            <th><fmt:message  key="adminBoard.name"/></th>
            <th><fmt:message  key="adminBoard.librarian"/></th>
            <th><fmt:message  key="adminBoard.actions"/></th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>
                <form action="../admin/update-access/${user.id}/${user.accessLevel}" method="POST">
                    <label class="switch">
                        <input type="checkbox"
                               onchange="submit()" ${user.accessLevel == 'LIBRARIAN' ?  'checked': ''} >
                        <span class="slider round"></span>
                    </label>
                </form>
            </td>
            <td>
                <form action="../admin/delete-user/${user.id}" method="POST">
                    <button type="submit" class="btn btn-danger">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </form>
                </c:forEach>
        </tbody>
    </table>
</div>
</fmt:bundle>
</body>
</html>


