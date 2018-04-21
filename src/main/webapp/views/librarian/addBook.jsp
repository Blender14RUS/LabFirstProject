<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<fmt:setLocale value="${language}"/>
<fmt:bundle basename="messages">
    <body>

    <jsp:include page="../layout/_menu.jsp"></jsp:include>
    <title><fmt:message  key="menu.addBook"/></title>
    <c:if test="${empty book}">
        <div class="col-sm-6">
            <form method="POST" action="/books/create-book">
                <div class="form-group col-md-12">
                    <label><fmt:message key="tab.title"/></label>
                    <input name="title" class="form-control" placeholder="War and Peace">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label><fmt:message key="tab.authors"/></label>
                        <input name="author" class="form-control" placeholder="Leo Tolstoy">
                    </div>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="tab.year"/></label>
                        <input name="year" class="form-control" placeholder="1869">
                    </div>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="tab.available"/></label>
                        <input name="available" class="form-control" placeholder="20">
                    </div>
                </div>
                <div class="form-group col-md-3 pull-right">
                    <div class="text-right">
                        <a href="${pageContext.request.contextPath}/books/" class="btn btn-danger"><fmt:message
                                key="addBook.cancel"/></a>
                        <button type="submit" class="btn btn-primary"><fmt:message key="menu.addBook"/></button>
                    </div>
                </div>
            </form>
        </div>
    </c:if>

    <c:if test="${not empty book}">
        <div class="col-sm-6">
            <form method="POST" action="/books/edit">
                <div class="form-group col-md-12">
                    <label><fmt:message key="tab.title"/></label>
                    <input name="title" class="form-control" value="${book.title}">
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label><fmt:message key="tab.authors"/></label>
                        <input name="authors" class="form-control"
                               value="${book.authors.toString().substring(1, book.authors.toString().length()-1)}">
                    </div>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="tab.year"/></label>
                        <input name="year" class="form-control" value="${book.year}">
                    </div>
                    <div class="form-group col-md-3">
                        <label><fmt:message key="tab.available"/></label>
                        <input name="available" class="form-control" value="${book.available}">
                    </div>
                </div>
                <div class="form-row">
                <div class="form-group col-md-2 pull-right">
                    <div class="text-right">
                        <a href="${pageContext.request.contextPath}/books/" class="btn btn-danger"><fmt:message
                                key="addBook.cancel"/></a>
                        <button type="submit" class="btn btn-primary" name="id" value="${book.id}"><fmt:message
                                key="addBook.save"/></button>
                    </div>
                </div>
                </div>
            </form>
        </div>
    </c:if>
    </body>
</fmt:bundle>

</html>
