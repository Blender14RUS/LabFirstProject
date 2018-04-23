<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>
<script src="../../resources/jquery/jquery-3.3.1.min.js"></script>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <head>
        <title>Your orders</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <body>

    <jsp:include page="../layout/_menu.jsp"></jsp:include>

    <div class="container">
        <h2><fmt:message key="userOrders.yourOrders"/></h2>
        <table class="table table-striped">
            <thead>
            <tr>
            <tbody>
            <tr>
                <th class="cell"><fmt:message key="tab.title"/></th>
                <th class="cell"><fmt:message key="tab.authors"/></th>
                <th class="cell"><fmt:message key="requestedBook.location"/></th>
                <th class="cell"><fmt:message key="requestedBook.status"/></th>
                <th class="cell"><fmt:message key="userOrders.cancel"/></th>
            </tr>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td class="cellVert">${order.book.title} (${order.book.year})</td>
                    <td class="cellVert">
                        <c:forEach items="${order.book.authors}" var="author">
                            ${author}<br>
                        </c:forEach>
                    </td>
                    <td class="cell">${order.location}</td>
                    <td class="cell">${order.status}</td>
                    <td class="cell"><c:if test="${(order.status eq 'REQUESTED')}">
                        <form action="${pageContext.request.contextPath}/user/delete-request" method="POST">
                            <button type="submit" class="btn btn-danger" name="orderId" value="${order.id}">
                                <span class="glyphicon glyphicon-trash"></span>
                            </button>
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
