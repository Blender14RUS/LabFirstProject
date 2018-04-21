<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>
<script src="../../resources/jquery/jquery-3.3.1.min.js"></script>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
<head>
    <title>Your orders</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="container">
    <h2><fmt:message  key="userOrders.yourOrders"/></h2>
    <table class="table table-striped">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th><fmt:message  key="tab.title"/></th>
            <th><fmt:message  key="tab.authors"/></th>
            <th><fmt:message  key="requestedBook.location"/></th></th>
            <th><fmt:message  key="requestedBook.status"/></th>
            <th></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.book.title} (${order.book.year})</td>
                <td>
                    <c:forEach items="${order.book.authors}" var="author">
                        ${author}<br>
                    </c:forEach>
                </td>
                <td>${order.location}</td>
                <td>${order.status}</td>
                <td><c:if test="${(order.status eq 'REQUESTED')}">
                    <form action="/user/delete-request" method="POST">
                        <button type="submit" class="btn btn-danger" name="orderId" value="${order.id}">
                            <span class="glyphicon glyphicon-trash"></span>
                        </button>
                        <input type="hidden" name="bookId" value="${order.book.id}"></input>
                    </form>
                </c:if></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</fmt:bundle>
</html>
