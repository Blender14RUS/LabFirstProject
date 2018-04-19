<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<jsp:include page="../common/_menu.jsp"></jsp:include>

<form action="/registration" method="POST">
    <input name="login" placeholder="Login" >
    <br>
    <input name="password" placeholder="Password" >

    <input type="submit" value="Create">
</form>

</body>