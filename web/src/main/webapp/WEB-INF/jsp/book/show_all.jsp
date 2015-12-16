<%-- 
    Document   : show_all
    Created on : 16.12.2015, 11:15:36
    Author     : xkubist
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ page import="cz.muni.fi.pa165.dto.BookDTO,java.util.List"%>

<x:base title="Members search result">
    <jsp:attribute name="content"> 
        <div class="panel-heading">Books</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Author</th>
                        <th>ISBN</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td><c:out value="${book.name}" /></td>
                            <td><c:out value="${book.authorName}" /></td>
                            <td><c:out value="${book.isbn}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>          
    </jsp:attribute>
</x:base>
