<%-- 
    Document   : create_book
    Created on : 9.12.2015, 18:09:12
    Author     : xkubist
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="cz.muni.fi.pa165.enums.BookState" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" modelAttribute="createBook">
		<div>
			<form:label path="name">Name</form:label>
			<div>
				<form:input path="name" />
				<form:errors path="name" />
			</div>
                        
                        <form:label path="authorName">Author Name</form:label>
			<div>
				<form:input path="authorName" />
				<form:errors path="authorName" />
			</div>
                        
                        <form:label path="isbn">ISBN</form:label>
			<div>
				<form:input path="isbn" />
				<form:errors path="isbn" />
			</div>
		</div>
		<button type="submit">Create book </button>
	</form:form>
</body>
</html>