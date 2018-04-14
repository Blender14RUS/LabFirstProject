<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link rel="stylesheet" type="text/css" href="css/custom.css"/>

<html>
<head>
    <title>Admin board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<div class="container">
    <h2>Users list</h2>
    <p>Set or take away the librarian rights</p>
    <table class="table table-striped">
        <thead>
        <tr>
    <tbody>
    <tr><th>ID</th><th>Login</th><th>Name</th><th>Date</th><th>Librarian</th><th>Actions</th></tr>
    <c:forEach items="${users}" var="user">
        <tr><td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>${user.dateofbirth}</td>
            <td><label class="switch">
                <input type="checkbox">
                <span class="slider round"></span>
            </label></td>
                <td><form action="/delete/${user.id}" method="POST">
                    <button type="submit" onclick="alarm()" class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span></button></form></td>
    </c:forEach>
    </tbody>
</table>
</div>
</body>
</html>

<script>
    function alarm() {
        alert('User has been deleted!' );
    }
</script>