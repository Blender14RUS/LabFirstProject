<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="../../css/custom.css"/>
<script src="../../jquery/jquery-3.3.1.min.js"></script>

<html>
<head>
    <title>Your orders</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../common/_menu.jsp"></jsp:include>

<div class="container">
    <h2>Your orders</h2>
    <table class="table table-striped">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th>Book authors</th>
            <th>Book title</th>
            <th>Order location</th>
            <th>Order status</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>
                    <c:forEach items="${order.book.authors}" var="author">
                        ${author}<br>
                    </c:forEach>
                </td>
                <td>${order.book.title}</td>
                <td>${order.location}</td>
                <td>${order.status}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
