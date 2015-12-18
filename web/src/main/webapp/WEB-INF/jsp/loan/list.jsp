<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<x:base title="Loan listing">
    <jsp:attribute name="content">
        <div class="panel-heading">Loans</div>
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
                        <td><c:out value="${loan.id}"/></td>
                        <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd"/></td>
                        <td><c:out value="${loan.returned ? 'Returned' : 'Loaned'}"/></td>
                        <td><c:out value="${loan.returnDate == null ? '-' : ''}"/> <fmt:formatDate
                                value="${loan.returnDate}"
                                pattern="yyyy-MM-dd"/></td>
                        <td><a href="<c:url value="/member/${loan.member.id}"/>"><c:out
                                value="${loan.member.givenName} ${loan.member.surname}"/></a></td>
                        <td><a href="<c:url value="/books/${loan.book.id}"/>"><c:out value="${loan.book.name}"/></a>
                        </td>
                        <td><c:choose>
                            <c:when test="${loan.returnBookState.getValue() eq 'new'}">
                                <c:out value="new"/>
                            </c:when>
                            <c:when test="${loan.returnBookState.getValue() eq 'light_damage'}">
                                <c:out value="light damage"/>
                            </c:when>
                            <c:when test="${loan.returnBookState.getValue() eq 'medium_damage'}">
                                <c:out value="medium damage"/>
                            </c:when>
                            <c:when test="${loan.returnBookState.getValue() eq 'heavy_damage'}">
                                <c:out value="heavy damage"/>
                            </c:when>
                            <c:when test="${loan.returnBookState.getValue() eq 'removed'}">
                                <c:out value="removed"/>
                            </c:when>
                            <c:otherwise>
                                <c:out value="-"/>
                            </c:otherwise>
                        </c:choose>
                        </td>
                        <td>
                            <c:if test="${member.isAdmin()}">
                            <c:if test="${!loan.returned}">
                            <a href="#myModal" class="returnTableBtn btn btn-default" data-toggle="modal"
                               data-loan-id=${loan.id}>Return</a>
                            </c:if>

                            <form method="post" action="${pageContext.request.contextPath}/loans/delete/${loan.id}">
                                <button type="submit" class="btn btn-default">Delete</button>
                            </form>
                            </c:if>
                        <td>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

    <c:if test="${member.isAdmin()}">
        <a href="${pageContext.request.contextPath}/loans/new" class="btn btn-default">New loan</a>
    </c:if>

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
                    <button type="button" id="cancel-btn" class="btn btn-default" data-dismiss="modal">Cancel
                    </button>
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