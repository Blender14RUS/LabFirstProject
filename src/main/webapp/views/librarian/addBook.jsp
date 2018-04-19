<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>

<jsp:include page="../common/_menu.jsp"></jsp:include>

<c:if test="${empty book}">
    <div class="col-sm-6">
        <form method="POST" action="/book/create-book">
            <div class="form-group col-md-12">
                <label>Title</label>
                <input name="title" class="form-control" placeholder="War and Peace">
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Author</label>
                    <input name="author" class="form-control" placeholder="Leo Tolstoy">
                </div>
                <div class="form-group col-md-3">
                    <label>Year</label>
                    <input name="year" class="form-control" placeholder="1869">
                </div>
                <div class="form-group col-md-3">
                    <label>Count available copy</label>
                    <input name="available" class="form-control" placeholder="20">
                </div>
            </div>
            <div class="form-group col-md-3 pull-right">
                <div class="text-right">
                    <a href="index" class="btn btn-danger">Cancel</a>
                    <button type="submit" class="btn btn-primary">Add book</button>
                </div>
            </div>
        </form>
    </div>
</c:if>

<c:if test="${not empty book}">
    <div class="col-sm-6">
        <form method="POST" action="/book/edit">
            <div class="form-group col-md-12">
                <label>Title</label>
                <input name="title" class="form-control" value="${book.title}">
            </div>
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label>Author</label>
                    <input name="authors" class="form-control" value="${book.authors.toString().substring(1, book.authors.toString().length()-1)}">
                </div>
                <div class="form-group col-md-3">
                    <label>Year</label>
                    <input name="year" class="form-control" value="${book.year}">
                </div>
                <div class="form-group col-md-3">
                    <label>Count available copy</label>
                    <input name="available" class="form-control" value="${book.available}">
                </div>
            </div>
            <div class="form-group col-md-3 pull-right">
                <div class="text-right">
                    <a href="index" class="btn btn-danger">Cancel</a>
                    <button type="submit" class="btn btn-primary" name="id" value="${book.id}">Save</button>
                </div>
            </div>
        </form>
    </div>
</c:if>

</body>
</html>
