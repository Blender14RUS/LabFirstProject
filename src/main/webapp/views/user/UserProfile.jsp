<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<head>
    <title>Profile</title>
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>

</body class="text-center">
<div class=" center-pill panel panel-info form-signin ">

    <div class="panel-heading">
        <h2 class="panel-title">${user.accessLevel}</h2>
    </div>
    <div class="panel-body">
        <div class="form-control">Login: ${user.login}
        </div>
        <form action="/profile/change-name/${user.login}" method="POST">
            <div class="form-control">
                <p>Name:</p>
                <input name="name" class="form-control" value="${user.name}"></td>

                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-pencil"></span>
                </button>

            </div>
        </form>
        <button type="submit" class="btn btn-default">Change password</button>
        </tbody>
        </table>
    </div>
</div>
</html>