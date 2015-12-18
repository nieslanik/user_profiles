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
                padding-left: 20px;
                padding-right: 20px;
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
                        <input class="list-group-item" style="width: 400px;" type="text" placeholder="No item" value="${book.name}" readonly/>
                    </div>
                </td>
                <td>
                    <form method="GET" action="${pageContext.request.contextPath}/loans/find_book">
                        <div class="input-group menu-search">
                            <input id="book" type="text" class="form-control" name="book"
                                   placeholder="Search books"/>
                            <input id="member" type="text" name="member" hidden="true" value="${member.id}"/>
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <td>
                    <label id="labelMember">Member</label>
                </td>
                <td>
                    <div class="textField">
                        <input class="list-group-item" style="width: 400px;" type="text" placeholder="No item" value="${member.givenName}" readonly/>
                    </div>
                </td>
                <td>
                    <form method="GET" action="${pageContext.request.contextPath}/loans/find_member">
                        <div class="input-group menu-search">
                            <input type="text" id="member" class="form-control" name="member"
                                   placeholder="Search members"/>
                            <input id="book" name="book" hidden="true" type="text" value="${book.id}"/>
                            <span class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </form>
                </td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <form:form method="POST" action="${pageContext.request.contextPath}/loans/create"
                               modelAttribute="createLoan">
                        <div>
                            <input id="memberId" name="memberId" hidden="true" type="text" value="${member.id}"/>
                            <input id="bookId" name="bookId" hidden="true" type="text" value="${book.id}"/>
                        </div>
                        <button type="submit" style="margin-left: 20px; margin-top: 20px">Create</button>
                    </form:form>
                </td>
                <td>
                </td>
            </tr>
            </tbody>
        </table>
    </jsp:attribute>
</x:base>