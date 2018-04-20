<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<jsp:include page="../layout/_menu.jsp"></jsp:include>

    <c:if test="${not empty alreadyRequsted}">
        <div class="alert alert-warning" role="alert" align="center">
            <strong>Oh snap!</strong> You already requested this book.
        </div>
    </c:if>


    <div class="col-sm-6">
        <div items="${book}" var="book">
            <div class="preview col-md-4">
                <div id="pic"><img class="img-responsive" src="https://cdn.rbt.ru/images/item/image/420/419265_1553993988_3e21c40135b4fa96cb7c9437993fde3a.jpg" /></div>
            </div>
            <div class="details col-md-8">
                <div class="form-row">
                    <h3>Title: ${book.title}</h3>
                </div>
                <div class="form-row">
                    <h3>Year: ${book.year} </h3>
                </div>
                <div class="form-row">
                    <h3>Author:
                        <span>
                            <c:forEach items="${book.authors}" var="author">
                                ${author}
                            </c:forEach>
                        </span>
                    </h3>
                </div>
                <c:if test="${(role eq 'ROLE_ADMIN') || (role eq 'ROLE_LIBRARIAN')}">
                    <div class="form-row">
                        <h3>Count available copy: ${book.available} </h3>
                    </div>
                </c:if>
                <br>
                <form action="/books/request/${book.id}" method="POST">
                    <div class="form-row">
                        <a href="${pageContext.request.contextPath}/books" class="btn btn-danger">Cancel</a>
                        <div class="btn-group">
                            <button type="button" data-toggle="dropdown" class="btn btn-success dropdown-toggle">Request book <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <div class="col-sm-12">
                                <li><button type="submit" class="btn btn-default btn-lg btn-block" name="location" value="HOME">Home</button></li>
                                <li class="divider"></li>
                                <li><button type="submit" class="btn btn-default btn-lg btn-block" name="location" value="READING_ROOM">Reading room</button></li>
                                </div>
                            </ul>

                        </div>
                    </div>
                </form>
                <br>
                <c:if test="${(role == 'ROLE_ADMIN') || (role == 'ROLE_LIBRARIAN')}">
                    <form action="/lib/addBook" method="POST">
                        <div class="form-row">
                            <button type="submit" class="btn btn-info" name="id" value="${book.id}">Edit book</button>
                        </div>
                    </form>
                </c:if>
            </div>
        </div>
    </div>
</body>
</html>
