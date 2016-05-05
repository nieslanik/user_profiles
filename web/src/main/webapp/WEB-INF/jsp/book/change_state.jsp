<%-- 
    Document   : change_price
    Created on : 14.12.2015, 14:38:03
    Author     : xkubist
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ page import="cz.muni.fi.pa165.enums.BookState" %>

<x:base title="Change book state">
    <jsp:attribute name="content">
        <form method="POST" action="<c:url value="/books/update/${id}" />">
        <table class="table table-default">
            <tr>
                <td class="key">Name</td>
                <td><c:out value="${book.name}" /></td>
            </tr>
            <tr>
                <td class="key">Author</td>
                <td><c:out value="${book.authorName}" /></td>
            </tr>
            <tr>
                <td class="key">ISBN</td>
                <td><c:out value="${book.isbn}"/></td>
            </tr>
            <tr>
                 <td><label for="state">State</label></td>
                 <td>
                     <select name="state" class="form-control">
                            <c:forEach var="s" items="${book.possibleStateTransitions}">
                                <option value="${s}">${s.value}</option>
                            </c:forEach>
                     </select>
                 </td>
            </tr>
        </table>
        <button type="submit" class="btn btn-default">Change state</button>
        </form>
    </jsp:attribute>
</x:base>