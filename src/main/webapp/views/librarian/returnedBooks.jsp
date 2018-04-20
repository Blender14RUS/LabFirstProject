<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<head>
    <title>Librarian board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>

<div class="container">
    <h2>Given books</h2>
    <table class="table table-striped">
        <thead>
        <tbody>
        <tr>
            <th>Order ID</th>
            <th>User</th>
            <th>Book</th>
            <th>Location</th>
            <th>Status</th>
            <th>Book returned</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.user.name}</td>
                <td>${order.book.title} (${order.book.year})</td>
                <td>${order.location}</td>
                <td>${order.status}</td>
                <td>
                    <form action="/books/return/${order.id}" method="POST">
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
