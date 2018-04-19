<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<body>

<jsp:include page="_menu.jsp"></jsp:include>

<div class="col-sm-6">
        <div items="${book}" var="book">
            <div class="preview col-md-4">
                <div id="pic"><img class="img-responsive" src="https://cdn.rbt.ru/images/item/image/420/419265_1553993988_3e21c40135b4fa96cb7c9437993fde3a.jpg" /></div>
            </div>
            <div class="details col-md-8">
                <div class="form-row">
                    <h3>Title: ${book.getTitle()}</h3>
                </div>
                <div class="form-row">
                    <h3>Year: ${book.getYear()} </h3>
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
                <c:if test="${not empty book}">
                    <div class="form-row">
                        <h3>Count available copy: ${book.available} </h3>
                    </div>
                </c:if>
                <form action="/book/request/${book.getId()}" method="POST">
                    <div class="form-row">
                        <a href="books" class="btn btn-danger">Cancel</a>
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
                <c:if test="${not empty book}">
                    <form action="/book/addBook" method="POST">
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
