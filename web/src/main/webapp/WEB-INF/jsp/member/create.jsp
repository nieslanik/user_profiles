<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<x:base title="Register new Member">
    <jsp:attribute name="content">
        <h1>Register new member</h1>
        <form:form method="POST" modelAttribute="createMember">
            <div class="form-group">
                <form:label path="member.givenName">Username</form:label>
                <form:input path="member.givenName" type="text" cssClass="form-control" />
                <form:errors path="member.givenName" />
                <form:label path="member.surname">Surname</form:label>
                <form:input path="member.surname" type="text" cssClass="form-control" />
                <form:errors path="member.surname" />
                <form:label path="member.email">E-mail</form:label>
                <form:input path="member.email" type="email" cssClass="form-control" />
                <form:errors path="member.email" />
                <form:label path="password">Password</form:label>
                <form:input path="password" type="password" cssClass="form-control" />
                <form:errors path="password" />
            </div>
            <button type="submit" class="btn btn-default">Register!</button>
        </form:form>
</jsp:attribute>
</x:base>