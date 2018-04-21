<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>?
<html>
<head>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
    <title><fmt:message  key="bookList.catalog"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1"   >
    <link rel="stylesheet" type="text/css" href="../../css/custom.css"/>
</fmt:bundle>
</head>

<body>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename = "messages">
<jsp:include page="../layout/_menu.jsp"></jsp:include>
<div class="container">
    <h2><fmt:message  key="bookList.catalog"/></h2>
    <form action="${pageContext.request.contextPath}/books" method="POST">
        <input name="bookTitle" class="form-control" placeholder="Enter the title">
        <fmt:message  key="bookList.showBooksThatNotAvailable"/>
        <label class="switch">
            <input type="checkbox" name="available">
            <span class="slider round"></span>
        </label>
        <fmt:message  key="bookList.sort"/>
        <select name="sort">
            <option value="alphabet">A-Z</option>
            <option value="alphabetRev">Z-A</option>
            <option value="year">Year</option>
            <option value="amountL">Amount L-H</option>
            <option value="amountH">Amount H-L</option>
        </select>
        <button type="submit" class="btn btn-primary"> Search
        </button>
    </form>
    <table class="table table-striped">
        <thead>
        <tr>
        <tbody>
        <tr>
            <th><fmt:message  key="tab.id"/></th>
            <th><fmt:message  key="tab.title"/></th>
            <th><fmt:message  key="tab.year"/></th>
            <th><fmt:message  key="tab.available"/></th>
            <th><fmt:message  key="tab.authors"/></th>

            <th></th>
        </tr>
        <c:forEach items="${books}" var="book">
            <tr>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.year}</td>
                <td>${book.available}</td>
                <td>
                    <c:forEach items="${book.authors}" var="author">
                        ${author}<br>
                    </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/books/view/${book.id}" method="POST">
                        <button type="submit" class="btn btn-primary"><fmt:message  key="tab.view"/>
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div id="pagination">
        <c:url value="/books" var="prev">
            <c:param name="page" value="${page - 1}"/>
        </c:url>
        <c:if test="${page > 1}">
            <a href="<c:out value="${prev}" />" class="pn prev">Prev</a>
        </c:if>
        <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
            <c:choose>
                <c:when test="${page == i.index}">
                    <span>${i.index}</span>
                </c:when>
                <c:otherwise>
                    <c:url value="/books" var="url">
                        <c:param name="page" value="${i.index}"/>
                    </c:url>
                    <a href='<c:out value="${url}" />'>${i.index}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:url value="/books" var="next">
            <c:param name="page" value="${page + 1}"/>
        </c:url>
        <c:if test="${page + 1 <= maxPages}">
            <a href='<c:out value="${next}" />' class="pn next">Next</a>
        </c:if>
    </div>
</div>
</fmt:bundle>
</body>
</html>
