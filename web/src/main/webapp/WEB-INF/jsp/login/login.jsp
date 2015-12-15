<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="x"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<x:base>
    <jsp:attribute name="title">
        Login
    </jsp:attribute>
    <jsp:attribute name="content">
        <h1>Login</h1>
        <form:form method="post" modelAttribute="member">
            <div class="form-group">
                <form:label path="memberEmail">E-mail: </form:label>
                <form:input path="memberEmail" type="text" cssClass="form-control"/>
                <form:errors path="memberEmail" />

                <form:label path="password">Password: </form:label>
                <form:input path="password" type="password" cssClass="form-control"/>
                <form:errors path="password" />
            </div>
            <input type="submit" value="Submit" /><br/>

            <div class="alert alert-danger">
                <c:out value="${err}" />
            </div>

        </form:form>
    </jsp:attribute>
</x:base>