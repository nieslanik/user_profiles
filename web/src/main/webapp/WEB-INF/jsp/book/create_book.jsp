<%-- 
    Document   : create_book
    Created on : 9.12.2015, 18:09:12
    Author     : xkubist
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ page import="cz.muni.fi.pa165.enums.BookState" %>
<x:base title="Members search result">
    <jsp:attribute name="content">
	<form:form method="POST" modelAttribute="createBook">
		<div class="form-group">
                        <div class="form-group">
                                <form:label path="name">Name</form:label>
				<form:input path="name" />
				<form:errors path="name" />
			</div>
                        
                        
			<div class="form-group">
                                <form:label path="authorName">Author Name</form:label>
				<form:input path="authorName" />
				<form:errors path="authorName" />
			</div>
                        
                        
			<div class="form-group">
                                <form:label path="isbn">ISBN</form:label>
				<form:input path="isbn" />
				<form:errors path="isbn" />
			</div>
		</div>
		<button type="submit">Create book </button>
        </form:form>
	
    </jsp:attribute>
</x:base>