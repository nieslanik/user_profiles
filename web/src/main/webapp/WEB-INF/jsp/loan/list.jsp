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
                                    <a href="${pageContext.request.contextPath}/loans/return_view?id=${loan.id}" class="btn btn-default">Return</a>
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

</jsp:attribute>
</x:base>