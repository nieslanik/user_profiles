<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Juraj Tomko
  Date: 12/10/2015
  Time: 17:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

    <form:form method="POST" action="${pageContext.request.contextPath}/loans/create" modelAttribute="createLoan" >
        <div>
            <form:label path="memberId">member id</form:label>
            <div>
                <form:input path="memberId"/>
                <form:errors path="memberId"/>
            </div>

            <form:label path="bookId">book id</form:label>
            <div>
                <form:input path="bookId"/>
                <form:errors path="bookId"/>
            </div>
        </div>
        <button type="submit">Create new Loan</button>
    </form:form>

</body>
</html>
