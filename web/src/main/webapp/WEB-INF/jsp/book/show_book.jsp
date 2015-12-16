<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<x:base title="Members search result">
    <jsp:attribute name="content">
	<div>Name: <c:out value="${book.name}"/></div>
        <div>Author Name: <c:out value="${book.authorName}"/></div>
        <div>ISBN: <c:out value="${book.isbn}"/></div>
        <div>State: <c:out value="${book.state}"/></div>
    </jsp:attribute>
</x:base>
