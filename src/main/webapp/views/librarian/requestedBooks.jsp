<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
<head>
    <title><fmt:message  key="requestedBook.librarianMenu"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>
<title><fmt:message  key="menu.requestedBooks"/></title>
<div class="container">
    <table class="table table-striped">
        <tr>
            <th><fmt:message  key="requestedBook.orderID"/></th>
            <th><fmt:message  key="requestedBook.user"/></th>
            <th><fmt:message  key="requestedBook.bookTitle"/></th>
            <th><fmt:message  key="requestedBook.location"/></th>
            <th><fmt:message  key="requestedBook.status"/></th>
            <th><fmt:message  key="requestedBook.giveBook"/></th>
            <th></th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.user.name}</td>
                <td>${order.book.title} (${order.book.year})</td>
                <td>${order.location}</td>
                <td>${order.status}</td>
                <td>
                    <form action="/books/give/${order.id}" method="POST">
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</fmt:bundle>
</html>
