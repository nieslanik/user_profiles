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

<table class="table">
  <thead>
  <tr>
    <th>Id</th>
    <th>Given name</th>
    <th>Surname</th>
    <th>Email</th>
  </tr>
  </thead>

  <tbody>
  <c:forEach items="${members}" var="member">
    <tr>
      <td><c:out value="${member.id}"/></td>
      <td><c:out value="${member.givenName}"/></td>
      <td><c:out value="${member.surname}"/></td>
      <td><c:out value="${member.email}"/></td>
      <td>
        <a href="${pageContext.request.contextPath}/loans/new?memberId=${member.id}&bookId=${param.book}" class="btn btn-primary">Choose</a>
      <td>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
