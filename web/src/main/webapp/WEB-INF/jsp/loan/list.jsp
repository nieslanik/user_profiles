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
    <style type="text/css">
        .bs-example {
            margin: 20px;
        }
    </style>
</head>
<body>

<table class="table">
    <thead>
    <tr>
        <th>id</th>
        <th>loan date</th>
        <th>state</th>
        <th>return date</th>
        <th>member</th>
        <th>book</th>
        <th>book return state</th>
    </tr>
    </thead>

    <tbody>
    <c:forEach items="${loans}" var="loan">
        <tr>
            <td>${loan.id}</td>
            <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd"/></td>
            <td>${loan.returned ? 'Returned' : 'Loaned'}</td>
            <td>${loan.returnDate == null ? '-' : ''} <fmt:formatDate value="${loan.returnDate}"
                                                                      pattern="yyyy-MM-dd"/></td>
            <td><c:out value="${loan.member.givenName} ${loan.member.surname}"/></td>
            <td><c:out value="${loan.book.name}"/></td>
            <td><c:choose>
                <c:when test="${loan.book.state == BookState.NEW}">
                    new
                </c:when>
                <c:when test="${loan.book.state == BookState.LIGHT_DAMAGE}">
                    light damage
                </c:when>
                <c:when test="${loan.book.state == BookState.MEDIUM_DAMAGE}">
                    medium damage
                </c:when>
                <c:when test="${loan.book.state == BookState.HEAVY_DAMAGE}">
                    heavy damage
                </c:when>
                <c:when test="${loan.book.state == BookState.REMOVED}">
                    removed
                </c:when>
                <c:otherwise>
                    -
                </c:otherwise>
            </c:choose></td>
            <td>
                <a href="#myModal" class="btn btn-sm btn-primary" data-toggle="modal">Return</a>
            <td>
            </td>
        </tr>
    </c:forEach>
    </tbody>


    <!-- Modal HTML -->
    <div id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">Book returning</h4>
                </div>
                <div class="modal-body">
                    <p>Choose state in which book was returned.</p>
                    <select id="statepicker" data-container="body">
                        <option value="1">No damage</option>
                        <option value="2">Light damaged</option>
                        <option value="3">Medium damaged</option>
                        <option value="4">Heavily damaged</option>
                        <option value="5">Not returned</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancel-btn" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" id="return-btn" class="btn btn-primary">Return</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            $('#return-btn').click(function () {
                var element = document.getElementById('statepicker');
                var state = element[element.selectedIndex].value;
                $('#myModal').modal('hide');
                alert(state.toString());
            });

        });
    </script>


</table>
</body>
</html>
