<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <body>
    <jsp:include page="../layout/_menu.jsp"></jsp:include>

    <c:if test="${not empty alreadyRequested}">
        <div class="alert alert-warning" role="alert" align="center">
            <strong><fmt:message key="viewBook.ohSnap"/></strong> <fmt:message
                key="registration.youHaveAlreadyRequestedThisBook"/>
        </div>
    </c:if>

    <div class="container">
        <div items="${book}" var="book">
            <div class="preview col-md-2">
            </div>
            <div class="preview col-md-4">
                <div id="pic"><img class="img-responsive"
                                   src="../../resources/img/bookDefault.jpg">
                </div>
            </div>
            <div class="details col-md-6">
                <div class="form-row">
                    <h3><fmt:message key="tab.title"/>: ${book.title}</h3>
                </div>
                <div class="form-row">
                    <h3><fmt:message key="tab.year"/>: ${book.year} </h3>
                </div>
                <div class="form-row">
                    <h3><fmt:message key="tab.authors"/>:
                        <span>
                            <c:forEach items="${book.authors}" var="author">
                                ${author}
                            </c:forEach>
                        </span>
                    </h3>
                </div>
                <security:authorize access="hasAnyRole('ADMIN', 'LIBRARIAN')">
                    <div class="form-row">
                        <h3><fmt:message key="tab.available"/>: ${book.available} </h3>
                    </div>
                </security:authorize>
                <br>
                <form action="/books/request/${book.id}" method="POST">
                    <div class="form-row">
                        <a href="${pageContext.request.contextPath}/books" class="btn btn-danger"><fmt:message key="addBook.cancel"/></a>
                        <div class="btn-group">
                            <button type="button" data-toggle="dropdown" class="btn btn-success dropdown-toggle">
                                <fmt:message key="viewBook.requestBook"/> <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <div class="col-sm-12">
                                <li><button type="submit" class="btn btn-default btn-lg btn-block" name="location" value="HOME"><fmt:message  key="viewBook.home"/></button></li>
                                <li class="divider"></li>
                                <li><button type="submit" class="btn btn-default btn-lg btn-block" name="location" value="READING_ROOM"><fmt:message  key="viewBook.readingRoom"/></button></li>
                                </div>
                            </ul>

                        </div>
                    </div>
                </form>
                <br>
                <div class="form-row">
                    <security:authorize access="hasAnyRole('ADMIN', 'LIBRARIAN')">
                        <form action="/lib/book-delete" method="POST">
                                <button type="submit" class="btn btn-danger" name="id" value="${book.id}"><fmt:message
                                        key="viewBook.delBook"/>
                                </button>
                        </form>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('ADMIN', 'LIBRARIAN')">
                        <form action="/lib/addBook" method="POST">

                                <button type="submit" class="btn btn-info" name="id" value="${book.id}"><fmt:message
                                        key="viewBook.editBook"/>
                                </button>
                            </form>
                    </security:authorize>
                </div>
            </div>
        </div>
    </div>
</body>
</fmt:bundle>
</html>
