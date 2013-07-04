<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title></title>
    <style type="text/css">
        .formfield * {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<sf:form class ="form-horizontal" method="POST" action="emailTemplate_Retrive"  modelAttribute="emailTemplate">
<legend>Email Template List</legend>

<table>
    <c:forEach var="emailTemlate" items="${emailTemplateList}">
        <tr>
            <td>${emailTemlate.templateType}</td>
        </tr>

        <tr>
            <td>${emailTemlate.subject}</td>
        </tr>
        <tr>
            <td>Hi ${emailTemlate.receiver},</td>
        </tr>
        <tr>
            <td>${emailTemlate.body}</td>
        </tr>
        <tr>
            <td>${emailTemlate.date}</td>
        </tr>

        <tr>
            <td>${emailTemlate.time}</td>
        </tr>
        <tr>
            <td>${emailTemlate.venue}</td>
        </tr>

    </c:forEach>
</table>
</sf:form>


</body>
</html>