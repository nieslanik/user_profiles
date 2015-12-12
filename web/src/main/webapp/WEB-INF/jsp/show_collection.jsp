<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <div>
        Name:
        <c:out value="${collection.name}" />
    </div>
    <div>
        Books in collection:
        <table>
            <tr>
                <th>Name</th>
                <th>Author</th>
                <th>ISBN</th>
            </tr>
            <c:forEach var="book" items="${collection.books}">
                <tr>
                    <td><c:out value="${book.name}" /></td>
                    <td><c:out value="${book.authorName}" /></td>
                    <td><c:out value="${book.isbn}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>