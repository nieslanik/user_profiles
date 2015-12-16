<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<x:base title="New loan creation">
    <jsp:attribute name="content">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

        <style>
            .nothing {
                border-collapse: collapse;
            }

            .nothing td {
                border: none;
            }

            .textField {
                margin-right: 20px
            }

            .searchField {
                padding-left: 20px;
                padding-right : 20px;
            }
        </style>

        <table class="nothing" cellspacing="0" cellpadding="0">
            <tbody>
            <tr>
                <td>
                    <label id="labelBook">Book</label>
                </td>
                <td>
                    <div class="textField">
                        <input class="list-group-item" type="text" placeholder="No item" readonly value="${book.name}"/>
                    </div>
                </td>
                <td>
                    <form:form method="GET" action="${pageContext.request.contextPath}/loans/find_book">
                        <div class="searchField">
                            <input id="book" class="list-group-item" name="book" type="text"
                                   placeholder="Search books"/>
                            <input id="member" name="member" hidden="true" type="text" value="${member.id}"/>
                        </div>
                    </form:form>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary">Find</button>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="labelMember">Member</label>
                </td>
                <td>
                    <div class="textField">
                        <input class="list-group-item" type="text" placeholder="No item" readonly
                               value="${member.givenName}"/>
                    </div>
                </td>
                <td>
                    <form:form method="GET" action="${pageContext.request.contextPath}/loans/find_member">
                        <div class="searchField">
                            <input id="member" class="list-group-item" name="member" type="text"
                                   placeholder="Search members"/>
                            <input id="book" name="book" hidden="true" type="text" value="${book.id}"/>
                        </div>
                    </form:form>
                </td>
                <td>
                    <button type="submit" class="btn btn-primary">Find</button>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <form:form method="POST" action="${pageContext.request.contextPath}/loans/create"
                               modelAttribute="createLoan">
                        <div class="textField">
                            <input id="memberId" name="memberId" hidden="true" type="text" value="${member.id}"/>
                            <input id="bookId" name="bookId" hidden="true" type="text" value="${book.id}"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Create</button>
                    </form:form>
                </td>
            </tr>
            </tbody>
        </table>

    </jsp:attribute>
</x:base>