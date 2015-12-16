<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title"%>
<%@ attribute name="head" fragment="true"%>
<%@ attribute name="content" fragment="true" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<sec:authorize var="loggedIn" access="isAuthenticated()" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel='stylesheet' href='${contextPath}/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
<link rel='stylesheet' href='${contextPath}/webjars/patternfly/2.6.0/dist/css/patternfly.min.css'>
<link rel='stylesheet' href='${contextPath}/webjars/patternfly/2.6.0/dist/css/patternfly-additions.min.css'>
<script src="${contextPath}/webjars/jquery/3.0.0-alpha1/jquery.min.js"></script>
<style>
.key {
    font-weight: bold;
    width: 0;
    white-space: nowrap;
}
</style>
<title>${title}</title>
<jsp:invoke fragment="head" />
</head>
<body>
    <nav class="navbar navbar-default">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextPath}">Library system</a>
            </div>
            <ul class="nav navbar-nav">
                <li><a href="${contextPath}/books/list">Books</a></li>
                <li><a href="${contextPath}/collection/list">Book collections</a></li>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="${contextPath}/loans/list">Loans</a></li>
                    <li><a href="${contextPath}/member/list">Members</a></li>
                </sec:authorize>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${loggedIn}">
                        <li class="navbar-text">Logged in as <sec:authentication property="principal.username" /></li>
                        <li><a href="${contextPath}/logout">Log out</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${contextPath}/login">Log in</a></li>
                    </c:otherwise>
                </c:choose>

                <li>
                    <form role="search" class="navbar-form" action="TODO">
                        <div class="input-group menu-search">
                            <input name="query" type="text" class="form-control" placeholder="Search books" /> <span
                                class="input-group-btn">
                                <button type="submit" class="btn btn-default">
                                    <span class="glyphicon glyphicon-search"></span>
                                </button>
                            </span>
                        </div>
                    </form>
                </li>
            </ul>
        </div>
    </nav>
    <div class="container"><jsp:invoke fragment="content" /></div>
</body>
</html>