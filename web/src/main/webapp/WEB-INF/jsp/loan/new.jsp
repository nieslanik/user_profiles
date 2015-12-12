<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Juraj Tomko
  Date: 12/10/2015
  Time: 17:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

    <form:form method="GET" action="${pageContext.request.contextPath}/loans/find_book">
        <label>Type book name</label>
        <div>
            <input id="book" name="book" type="text" value="${book.name}"/>
            <input id="member" name="member" hidden="true" type="text" value="${member.id}"/>
            <button type="submit" class="btn btn-primary">Find</button>
        </div>
    </form:form>

    <form:form method="GET" action="${pageContext.request.contextPath}/loans/find_member">
        <label>Type member name</label>
        <div>
            <input id="member" name="member" type="text" value="${member.givenName}"/>
            <input id="book" name="book" hidden="true" type="text" value="${book.id}"/>
            <button type="submit" class="btn btn-primary">Find</button>
        </div>
    </form:form>

    <form:form method="POST" action="${pageContext.request.contextPath}/loans/create" modelAttribute="createLoan">
        <div>
            <input id="memberId" name="memberId" hidden="true" type="text" value="${member.id}"/>
            <input id="bookId" name="bookId" hidden="true" type="text" value="${book.id}"/>
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form:form>

</body>
</html>
