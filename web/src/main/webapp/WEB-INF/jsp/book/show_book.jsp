<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<x:base>
    <jsp:attribute name="title">
        <c:out value="${book.name}" /> - book detail
    </jsp:attribute>
    <jsp:attribute name="content">
        <h1>
            <c:out value="${book.name}" /> - book detail</h1>
        <table class="table table-default">
            <tr>
                <td class="key">Author name</td>
                <td><c:out value="${book.authorName}" /></td>
            </tr>
            <tr>
                <td class="key">ISBN</td>
                <td><c:out value="${book.isbn}" /></td>
            </tr>
            <tr>
                <td class="key">Current state</td>
                <td><c:out value="${book.state.value}" /></td>
            </tr>
            <tr>
                <td class="key">Presently loaned</td>
                <td><c:out value="${book.loaned? 'Yes': 'No'}" /></td>
            </tr>
        </table>
        <sec:authorize access="hasRole('ADMIN')">
            <div class="panel panel-default">
                <div class="panel-heading">Loan history</div>
                <table class="table">
                    <thead>
                        <tr>
                            <th>Member name</th>
                            <th>Email</th>
                            <th>Loan date</th>
                            <th>Return date</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${book.loans}" var="loan">
                            <tr>
                                <td>
                                    <a href="<c:url value="/member/${loan.member.id}"/>">
                                        <c:out value="${loan.member.givenName}" /> <c:out value="${loan.member.surname}" />
                                    </a>
                                </td>
                                <td><c:out value="${loan.member.email}" /></td>
                                <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd" /></td>
                                <td>
                                    <fmt:formatDate value="${loan.returnDate}" pattern="yyyy-MM-dd" />
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </sec:authorize>
    </jsp:attribute>
</x:base>
