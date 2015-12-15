<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<x:base>
    <jsp:attribute name="title">
        <c:out value="${collection.name}" /> - book collection details
    </jsp:attribute>
    <jsp:attribute name="content">
        <h1>
            <c:out value="${collection.name}" /> - book collection details
        </h1>
        <div class="panel panel-default">
        <div class="panel-heading">Books in collection</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Author</th>
                        <th>ISBN</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${collection.books}">
                        <tr>
                            <td><c:out value="${book.name}" /></td>
                            <td><c:out value="${book.authorName}" /></td>
                            <td><c:out value="${book.isbn}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <form action="${pageContext.request.contextPath}/collection/${collection.id}/update">
            <button class="btn btn-default">Modify collection</button>
        </form>
        <form action="${pageContext.request.contextPath}/collection/${collection.id}/delete" method="POST"
                onSubmit="return confirm('Are you sure you want to permanently delete the collection?')">
            <button class="btn btn-default">Delete collection</button>
        </form>
    </jsp:attribute>
</x:base>