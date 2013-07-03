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
<div class="alert alert-success">
    <a class="close" data-dismiss="alert">×</a>
    <strong>Success!</strong>Your Application was saved successfully and notification email was sent.
</div>

<div class="padding-bottom-10">
    <h5>you have successfully applied to the ${cvApplication.applicationName} with following Values.</h5>
</div>

      <table>
          <tr><td><label>Your Name :</label></td><td><label for="${cvApplication.userApplied.applicantName}">${cvApplication.userApplied.applicantName}</label></td></tr>
          <tr><td><label>Applied Possition : </label></td><td><label for="${cvApplication.cvApplicationTemplate.cvHeaderEn}">${cvApplication.cvApplicationTemplate.cvHeaderEn}</label></td></tr>

          <c:forEach items="${cvApplication.cvApplicationTemplate.cvApplicationSectionList}" var="cvApplicationSectionList">
          <tr><td colspan="2"><label for="${cvApplicationSectionList.sectionNameEn}"><b>${cvApplicationSectionList.sectionNameEn}</b></label></td></tr>

              <c:forEach var="cvApplicationFieldList" items="${cvApplicationSectionList.cvApplicationFieldList}">
             <tr><td><label for="${cvApplicationFieldList.applicationFieldDictionary.labelEn}">${cvApplicationFieldList.applicationFieldDictionary.labelEn}     :       </label></td><td><label for="${cvApplicationFieldList.fieldValue}">${cvApplicationFieldList.fieldValue}</label></td></tr>
           </c:forEach>
       </c:forEach>
      </table>
</body>
</html>