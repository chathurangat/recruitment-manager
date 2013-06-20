<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <link href="/resources/css/bootstrap-responsive.css" rel="stylesheet">

</head>
<body>



<div class="container">

    <div class="row">


        <div class="span8">


    <sf:form class ="form-horizontal" method="POST" action="register"  modelAttribute="cvApplicationSection">
    <legend>Add New CV sections to the CV section register</legend>
    <div class="control-group">
    <sf:label path="sectionNameEn" >
        <spring:message code="label.name.in.english"/>
    </sf:label>

    <sf:input path="sectionNameEn" size="100" />
    <sf:errors path="sectionNameEn" cssClass="text-error"/>
    </div>

    <div class="control-group">
    <sf:label path="sectionNameSi" >
        <spring:message code="label.name.in.sinhala"/>
    </sf:label>

    <sf:input path="sectionNameSi" size="100" />
    </div>

    <div class="control-group">
    <sf:label path="sectionNameTa" >
        <spring:message code="label.name.in.tamil"/>
    </sf:label>

    <sf:input path="sectionNameTa" size="100" />
    </div>

    <div class="control-group">
        <label class="control-label" for="submit"></label>
        <div class="controls">
            <button type="submit" name="submit" class="btn btn-success" rel="tooltip" title="first tooltip" id="submit" >Submit</button>

        </div>
</sf:form>


</body>
</html>