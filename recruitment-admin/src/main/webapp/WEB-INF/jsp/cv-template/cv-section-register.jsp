<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

CV section register

<sf:form method="POST" action="register"  modelAttribute="cvApplicationSection">


    <sf:label path="sectionNameEnglish" >
        <spring:message code="label.name.in.english"/>
    </sf:label>
    <sf:input path="sectionNameEnglish" size="100" />
    <sf:errors path="sectionNameEnglish" cssClass="text-error"/>

    <sf:label path="sectionNameSinhala" >
        <spring:message code="label.name.in.sinhala"/>
    </sf:label>
    <sf:input path="sectionNameSinhala" size="100" />

    <sf:label path="sectionNameTamil" >
        <spring:message code="label.name.in.tamil"/>
    </sf:label>
    <sf:input path="sectionNameTamil" size="100" />

    <input name="submit" type="submit" />

</sf:form>


</body>
</html>