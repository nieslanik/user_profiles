<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<x:base title="Members search result">
    <jsp:attribute name="content">
	<table class="table table-default">
            <tr>
                <td class="key">name</td>
                <td><c:out value="${book.name}" /></td>
            </tr>
            <tr>
                <td class="key">author</td>
                <td><c:out value="${book.authorName}" /></td>
            </tr>
            <tr>
                <td class="key">ISBN</td>
                <td><c:out value="${book.isbn}" /></td>
            </tr>
            <tr>
                <td class="key">State</td>
                <td><c:out value="${book.state}" /></td>
            </tr>
     </table>
    </jsp:attribute>
    
</x:base>
