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
</head>
<body>
<c:forEach items="${loans}" var="loan">
    <tr>
        <td>${loan.id}</td>
        <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd"/></td>
        <td><fmt:formatDate value="${loan.returnDate}" pattern="yyyy-MM-dd"/></td>
        <td><c:out value="${loan.book.name}"/></td>
        <td><c:out value="${loan.returned}"/></td>
        <td>
            <my:a href="/loan/view/${loan.id}" class="btn btn-primary">View</my:a>
        </td>
        <td>
            <form method="post" action="${pageContext.request.contextPath}/loan/delete/${loan.id}">
                <button type="submit" class="btn btn-primary">Delete</button>
            </form>
        </td>
    </tr>
</c:forEach>
</body>
</html>
