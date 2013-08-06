<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>

</head>
<body>
<form:form class ="form-horizontal" method="POST" action="calendar_register"  modelAttribute="calendar">
    <fieldset><legend>Test calendar</legend>
        <table>
            <tr>
                <td> Event Title</td>
                <td><input type="text"  /></td>
            </tr>
            <tr>
                <td>Event Time</td>
                <td><input type="time"  /></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text"  /></td>
            </tr>
            <tr>
                <td><br />
                </td>
                <td><input type="submit" value="Add" /></td>
            </tr>
        </table>
    </fieldset>
</form:form>
</body>
</html>