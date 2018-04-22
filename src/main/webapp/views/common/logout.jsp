<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="logout.loggedOut"/></title></head>
</fmt:bundle>
<body>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
<% session.removeAttribute("username");
    session.removeAttribute("password");
    session.invalidate(); %>
<h1><fmt:message key="logout.loggedOut"/>
</h1>

</body>
</fmt:bundle>
</html>
