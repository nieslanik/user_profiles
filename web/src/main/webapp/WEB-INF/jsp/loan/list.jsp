<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<x:base title="Loan listing">
    <jsp:attribute name="content">
        <c:if test="${not empty alert_success}">
            <div class="alert alert-success" role="alert"><c:out value="${alert_success}"/></div>
        </c:if>
        <c:if test="${not empty alert_warning}">
            <div class="alert alert-warning" role="alert"><c:out value="${alert_warning}"/></div>
        </c:if>

        <table class="table">
            <thead>
            <tr>
                <th>Id</th>
                <th>Loan date</th>
                <th>State</th>
                <th>Return date</th>
                <th>Member</th>
                <th>Book</th>
                <th>Book return state</th>
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
                        <c:when test="${loan.returnBookState.getValue() eq 'new'}">
                            new
                        </c:when>
                        <c:when test="${loan.returnBookState.getValue() eq 'light_damage'}">
                            light damage
                        </c:when>
                        <c:when test="${loan.returnBookState.getValue() eq 'medium_damage'}">
                            medium damage
                        </c:when>
                        <c:when test="${loan.returnBookState.getValue() eq 'heavy_damage'}">
                            heavy damage
                        </c:when>
                        <c:when test="${loan.returnBookState.getValue() eq 'removed'}">
                            removed
                        </c:when>
                        <c:otherwise>
                            -
                        </c:otherwise>
                    </c:choose>
                    </td>
                    <td>
                        <c:if test="${!loan.returned}">
                        <a href="#myModal" class="returnTableBtn btn-primary btn-sm" data-toggle="modal"
                           data-loan-id=${loan.id}>Return</a>
                        </c:if>

                        <form method="post" action="${pageContext.request.contextPath}/loans/delete/${loan.id}">
                            <button type="submit" class="btn btn-primary btn-sm">Delete</button>
                        </form>
                    <td>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

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
                        <option value="new">No damage</option>
                        <option value="light_damage">Light damaged</option>
                        <option value="medium_damage">Medium damaged</option>
                        <option value="heavy_damage">Heavily damaged</option>
                        <option value="removed">Removed</option>
                    </select>
                </div>
                <div class="modal-footer">
                    <button type="button" id="cancel-btn" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    <button type="button" id="confirm-btn" class="btn btn-primary">Confirm</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(function () {
            $('#confirm-btn').click(function () {
                var element = document.getElementById('statepicker');
                var state = element[element.selectedIndex].value;
                var loanId = $('#confirm-btn').attr('loan-id');
                var url = "${pageContext.request.contextPath}/loans/return/" + loanId;
                $('#myModal').modal('hide');

                $.post(url, {bookStateStr: state}, function (data) {
                    window.location.reload();
                });
            });
        });

        $(function () {
            $('.returnTableBtn').click(function () {
                var loanId = $(this).data('loan-id');
                $("#confirm-btn").attr('loan-id', loanId);
            })
        })
    </script>
    </jsp:attribute>
</x:base>