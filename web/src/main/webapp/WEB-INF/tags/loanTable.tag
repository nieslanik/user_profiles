<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ attribute name="loans" required="true" type="java.util.List"%>
<%@ attribute name="showMember"%>
<%@ attribute name="showBook"%>
<%@ attribute name="showReturn"%>
<table class="table">
    <thead>
        <tr>
            <c:if test="${showBook}">
                <th>Book</th>
                <th>Book author</th>
            </c:if>
            <c:if test="${showMember}">
                <th>Member</th>
                <th>Memeber email</th>
            </c:if>
            <th>Loan date</th>
            <c:if test="${showReturn}">
                <th>Return date</th>
                <th>Book return state</th>
            </c:if>
            <sec:authorize access="hasRole('ADMIN')">
                <th>Actions</th>
            </sec:authorize>
        </tr>
    </thead>

    <tbody>
        <c:forEach items="${loans}" var="loan">
            <tr>
                <c:if test="${showBook}">
                    <td><a href="<c:url value="/books/${loan.book.id}"/>"><c:out value="${loan.book.name}" /></a></td>
                    <td><c:out value="${loan.book.authorName}" /></td>
                </c:if>
                <c:if test="${showMember}">
                    <td><a href="<c:url value="/member/${loan.member.id}"/>"><c:out
                                value="${loan.member.givenName} ${loan.member.surname}" /></a></td>
                    <td><c:out value="${loan.member.email}" /></td>
                </c:if>
                <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd" /></td>
                <c:if test="${showReturn}">
                    <td>${loan.returnDate == null ? '-' : ''}<fmt:formatDate value="${loan.returnDate}"
                            pattern="yyyy-MM-dd" /></td>
                    <td>${loan.returnBookState.value}</td>
                </c:if>
                <sec:authorize access="hasRole('ADMIN')">
                    <td><c:if test="${!loan.returned}">
                            <a href="<c:url value="/loans/return/${loan.id}">
                                            <c:param name="redir">${requestScope['javax.servlet.forward.request_uri']}</c:param>
                                     </c:url>" class="btn btn-default">Return</a>
                        </c:if>

                        <form method="post" style="display:inline" "action="<c:url value="/loans/delete/${loan.id}" />">
                            <button type="submit" class="btn btn-default">Delete</button>
                        </form></td>
                </sec:authorize>
            </tr>
        </c:forEach>
    </tbody>
</table>
