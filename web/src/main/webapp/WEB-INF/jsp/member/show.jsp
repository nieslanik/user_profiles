<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="name">
    <c:out value="${member.givenName}" />
    <c:out value="${member.surname}" />
</c:set>
<x:base>
    <jsp:attribute name="title">
        ${name} - member detail
    </jsp:attribute>
    <jsp:attribute name="content">
        <h1>${name}</h1>
        <table class="table table-default">
            <tr>
                <td class="key">ID</td>
                <td><c:out value="${member.id}" /></td>
            </tr>
            <tr>
                <td class="key">E-mail</td>
                <td><c:out value="${member.email}" /></td>
            </tr>
            <tr>
                <td class="key">Date of registration</td>
                <td><fmt:formatDate value="${member.registrationDate}" pattern="yyyy-MM-dd" /></td>
            </tr>
        </table>

        <div class="panel panel-default">
            <div class="panel-heading">Active Loans</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Book</th>
                        <th>Loan Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${activeloans}" var="loan">
                        <tr>
                            <td><c:out value="${loan.book.name}" /></td>
                            <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">Returned Loans</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>Book</th>
                        <th>Loan Date</th>
                        <th>Return Date</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${returnedloans}" var="loan">
                        <tr>
                            <td><c:out value="${loan.book.name}" /></td>
                            <td><fmt:formatDate value="${loan.loanDate}" pattern="yyyy-MM-dd" /></td>
                            <td><fmt:formatDate value="${loan.returnDate}" pattern="yyyy-MM-dd" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <sec:authorize access="hasRole('ADMIN')">
            <div cssClass="form-control">

                <a href="${member.id}/update" class="btn btn-default">Update member</a>

            </div>
        </sec:authorize> 

    </jsp:attribute>
</x:base>