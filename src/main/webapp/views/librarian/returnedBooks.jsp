<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <head>
        <title><fmt:message key="requestedBook.librarianMenu"/></title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

    <jsp:include page="../layout/_menu.jsp"></jsp:include>
    <title><fmt:message key="menu.requestedBooks"/></title>
    <div class="container">
        <h2></h2>
        <table class="table table-striped">
            <tr>
                <th class="cell"><fmt:message key="requestedBook.orderID"/></th>
                <th class="cell"><fmt:message key="requestedBook.user"/></th>
                <th class="cell"><fmt:message key="requestedBook.bookTitle"/></th>
                <th class="cell"><fmt:message key="requestedBook.location"/></th>
                <th class="cell"><fmt:message key="requestedBook.status"/></th>
                <th class="cell"><fmt:message key="requestedBook.bookReturned"/></th>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td class="cell">${order.id}</td>
                    <td class="cell">${order.user.name}</td>
                    <td class="cellVert">${order.book.title} (${order.book.year})</td>
                    <td class="cell">${order.location}</td>
                    <td class="cell">${order.status}</td>
                    <td class="cell">
                        <form action="/books/return/${order.id}" method="POST">
                            <button type="submit" class="btn btn-success">
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
