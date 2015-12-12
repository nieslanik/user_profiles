<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet'
    href='${pageContext.request.contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<link rel='stylesheet'
    href='${pageContext.request.contextPath}/webjars/patternfly/2.6.0/dist/css/patternfly.min.css'>
<link rel='stylesheet'
    href='${pageContext.request.contextPath}/webjars/patternfly/2.6.0/dist/css/patternfly-additions.min.css'>
<script src="${pageContext.request.contextPath}/webjars/jquery/3.0.0-alpha1/jquery.min.js"></script>
<title>Create book collection</title>
<script>
    function selectRow(row) {
        row = $(row);
        if (row.parents("#available-table").length > 0) {
            row.detach().appendTo("#selected-table tbody");
        } else {
            row.detach().appendTo("#available-table tbody");
        }
    }
    function prepareBooks() {
        var data = $("#selected-table tbody tr").get().map(function(x) {
            return x.getAttribute("data-book-id");
        });
        $("#selected-books").val(data.join(","));
    }
</script>
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${pageContext.request.contextPath}">Library
                    system</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="TODO">Books</a></li>
                <li class="active"><a href="TODO">Book collections</a></li>
                <li><a href="TODO">Loans</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="TODO">Log in</a></li>
                <li>
                    <form role="search" class="navbar-form" action="TODO">
                        <div class="input-group menu-search">
                            <input name="query" type="text" class="form-control"
                                placeholder="Search books" /> <span
                                class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container">
        <h1>Create new book collection</h1>
        <form:form method="POST" modelAttribute="createCollection"
            onSubmit="prepareBooks()">
            <div class="form-group">
                <form:label path="name">Name</form:label>
                <form:input path="name" cssClass="form-control" />
                <form:errors path="name" />
            </div>
            <div class="panel panel-default">
                <div class="panel-body">Add books to collection by clicking on them
                    in "Available books" table.</div>
            </div>
            <div class="row">
                <div class="col-xs-6">
                    <div class="panel panel-default" id="available-panel">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">Available books</div>
                                <div class="col-xs-6">
                                    <%-- TODO filter --%>
                                    <div class="input-group">
                                        <input type="text" id="filter"
                                            placeholder="Filter" class="form-control" />
                                        <span class="input-group-addon"> <i
                                            class="glyphicon glyphicon-filter"> </i>
                                        </span>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <table class="table table-hover" id="available-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Author</th>
                                    <th>ISBN</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="book" items="${allBooks}">
                                    <tr onClick="selectRow(this)"
                                        data-book-id="${book.id}">
                                        <td><c:out value="${book.name}" /></td>
                                        <td><c:out value="${book.authorName}" /></td>
                                        <td><c:out value="${book.isbn}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="col-xs-6">
                    <div class="panel panel-default" id="selected-panel">
                        <div class="panel-heading">Selected books</div>
                        <table class="table table-hover" id="selected-table">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Author</th>
                                    <th>ISBN</th>
                                </tr>
                            </thead>
                            <tbody></tbody>
                        </table>
                    </div>
                </div>
            </div>
            <input type="hidden" name="bookIds" id="selected-books" />
            <button type="submit" class="btn btn-default">Create book collection</button>
        </form:form>
    </div>
</body>
</html>