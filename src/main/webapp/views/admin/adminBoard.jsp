<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script src="../../jquery/jquery-3.3.1.min.js"></script>

<html>
<head>
    <title>Admin board</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<jsp:include page="../common/_menu.jsp"></jsp:include>

<div class="container">
    <h2>User list</h2>
    <p>Set or take away the librarian rights</p>
    <table class="table table-striped ">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Name</th>
            <th>Librarian</th>
            <th>Actions</th>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
            <td>${user.name}</td>
            <td>
                <form action="user/update-access/${user.id}/${user.accessLevel}" method="POST">
                    <label class="switch">
                        <input type="checkbox"
                               onchange="submit()" ${user.accessLevel == 'LIBRARIAN' ?  'checked': ''} >
                        <span class="slider round"></span>
                    </label>
                </form>
            </td>
            <td>
                <form action="admin/delete-user" method="POST">
                    <button type="submit" class="btn btn-danger">
                        <span class="glyphicon glyphicon-trash"></span>
                    </button>
                </form>
                </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>


