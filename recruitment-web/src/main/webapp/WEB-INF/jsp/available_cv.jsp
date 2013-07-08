<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Available Cv Template for Vacancy</title>
    <style type="text/css">
        .formfield * {
            vertical-align: middle;
        }
    </style>
</head>
<body>
<fieldset>
    <legend>
        Available Cv Application for Vacancy

        <table>
            <ul class="nav nav-list">
                <li class="nav-header">Select the Cv Application here !</li>
            <c:forEach items="${cvApplicationTemplate}" var="cvApplicationTemplate">

                    <li><label><a href="http://rec.admin.hsenidmobile.com:8080/recruitment-web/user/applicant/apply?id=${cvApplicationTemplate.id}">${cvApplicationTemplate.cvHeaderEn}</a></label></li>
            </c:forEach>
            </ul>
        </table>
    </legend>
</fieldset>
</body>
</html>