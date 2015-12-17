<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<x:base title="${action} member">
    <jsp:attribute name="content">
        <h1>${action} member</h1>
        <form:form method="POST" modelAttribute="createMember">
            <div class="form-group">
                <div class="form-group">
                    <form:label path="givenName">Given name</form:label>
                    <form:input path="givenName" type="text" cssClass="form-control" />
                    <form:errors path="givenName" />
                </div>
                <div class="form-group">
                    <form:label path="surname">Surname</form:label>
                    <form:input path="surname" type="text" cssClass="form-control" />
                    <form:errors path="surname" />
                </div>
                <div class="form-group">
                    <form:label path="email">E-mail</form:label>
                    <form:input path="email" type="email" cssClass="form-control" />
                    <form:errors path="email" />
                </div>
                <div class="form-group">
                    <form:label path="password">Password</form:label>
                    <form:input path="password" type="password" cssClass="form-control" />
                    <form:errors path="password" />
                </div>
                <div role="ADMIN" class="form-group">
                    <form:label path="isAdmin">Is member admin? </form:label>
                    <form:checkbox path="isAdmin" cssClass="form-control" />
                    <form:errors path="isAdmin" />
                </div>
            </div>
            <button type="submit" class="btn btn-default">${action} member</button>
        </form:form>
    </jsp:attribute>
</x:base>