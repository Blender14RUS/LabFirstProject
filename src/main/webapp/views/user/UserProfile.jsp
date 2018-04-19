<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" type="text/css" href="../../resources/css/custom.css"/>

<html>
<head>
    <title>Profile</title>
</head>
<body>

<jsp:include page="../layout/_menu.jsp"></jsp:include>

</body>
</html>
<div class="col-md-2 col-md-offset-5 center-pill">
    <div class="panel panel-info ">
        <div class="panel-heading">
            <h2 class="panel-title">${user.accessLevel}</h2>
        </div>
        <div class="panel-body">
            <table class="table table-condensed table-hover table-borderless">
                <tbody>
                <tr>
                    <td>Login:</td>
                    <td>${user.login}</td>
                    <td>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>Change password</td>
                    <td></td>
                    <td>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                    </td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td>${user.name}</td>
                    <td>
                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-pencil"></span>
                        </button>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>