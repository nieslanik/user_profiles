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
<%@ page import="cz.muni.fi.pa165.dto.BookDTO,java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>   
                        <c:forEach items="${books}" var="book">
                            <div> <c:out value="Name:${book.name} Author:${book.authorName} ISBN:${book.isbn} State:${book.state}"/></div>
                        </c:forEach>
                        
</body>
</html>
