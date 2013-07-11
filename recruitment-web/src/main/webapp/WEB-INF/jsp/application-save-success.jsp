<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hsenid
  Date: 6/30/13
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div class="row">
    <div class="control-group">
        <div class="span9">
            <div class="alert alert-success">
                <a class="close" data-dismiss="alert">×</a>
                <strong>Success!</strong>Your Application was saved successfully and notification email was sent.
            </div>

            <div class="padding-bottom-10">
                <h5>you have successfully applied to the ${cvApplication.applicationName} with following Values.</h5>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="control-group">
        <div class="span2">
        </div>
    </div>
    <div class="control-group">
        <div class="span7">

<div class="row">
    <div class="control-group">
        <div class="span5">
            <label><b>Your Name :</b></label>
        </div>
        <div class="span5">
            <label for="${cvApplication.userApplied.applicantName}"><b>${cvApplication.userApplied.applicantName}</b></label>
        </div>
    </div>
</div>

<div class="row">
    <div class="control-group">
        <div class="span5">
            <label><b>Applied Possition : </b></label>
        </div>
        <div class="span5">
            <label for="${cvApplication.cvApplicationTemplate.cvHeaderEn}"><b>${cvApplication.cvApplicationTemplate.cvHeaderEn}</b></label>
        </div>
    </div>
</div>
<br/>
<div class="row">
    <div class="control-group">
        <div class="span10">
            <label><b>Details you have entered !</b></label>
        </div>
    </div>
</div>
<br/>

<c:forEach items="${cvApplication.cvApplicationTemplate.cvApplicationSectionList}" var="cvApplicationSectionList">
    <div class="row">
        <div class="control-group">
            <div class="span10">
                <label for="${cvApplicationSectionList.sectionNameEn}"><b>${cvApplicationSectionList.sectionNameEn}</b></label>
            </div>
        </div>
    </div>

    <c:forEach var="cvApplicationFieldList" items="${cvApplicationSectionList.cvApplicationFieldList}">

        <div class="row">
            <div class="control-group">
                <div class="span5">
                    <label for="${cvApplicationFieldList.applicationFieldDictionary.labelEn}">${cvApplicationFieldList.applicationFieldDictionary.labelEn} : </label>
                </div>
                <div class="span5">
                    <label for="${cvApplicationFieldList.fieldValue}">${cvApplicationFieldList.fieldValue}</label>
                </div>
            </div>
        </div>
    </c:forEach>
</c:forEach>
        </div>
    </div>
</div>

</body>
</html>