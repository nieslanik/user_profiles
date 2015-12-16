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

<x:base title="Members search result">
    <jsp:attribute name="content">
	<form method="POST">
                    <div>Name: <c:out value="${book.name}"/></div>
                    <div>Author Name: <c:out value="${book.authorName}"/></div>
                    <div>ISBN: <c:out value="${book.isbn}"/></div>
		<div>   
                        <label for="state">State</label>
                        <select name="state" >
                        
                        <%
                        BookState state=(BookState)pageContext.findAttribute("state");    
                        for(BookState bookState:BookState.values()){
                            if(bookState.compareTo(state)>=0){%>
                            <option value="<%=bookState%>"><%=bookState%></option>
                            
                        <%}}%>
                        </select>
		</div>
		<button type="submit">Change state</button>
	</form>
    </jsp:attribute>
</x:base>