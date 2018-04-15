<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="css/custom.css"/>

<html>
<head>
    <title>Librarian board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<div class="container">
    <h2>Requests for books issue</h2>
    <p>Accept or decline issuing books</p>
    <table class="table table-striped">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th>Order ID</th>
            <th>User ID</th>
            <th>Book ID</th>
            <th>Location</th>
            <th>Status</th>
            <th>Give book</th>
        </tr>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td>${order.userId}</td>
                <td>${order.bookId}</td>
                <td>${order.location}</td>
                <td>${order.status}</td>
                <td>
                    <form action="/give/${order.id}" method="POST">
                        <label class="switch">
                            <input type="checkbox"
                                   onchange="submit()">
                        </label>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
