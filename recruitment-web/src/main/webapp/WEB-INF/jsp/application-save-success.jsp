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


  <fieldset name="${cvApplication.applicationName}">
      <legend>
          ${cvApplication.applicationName}

      <table>
          <tr><td>Your Name : </td><td>${cvApplication.userApplied.applicantName}</td></tr>
          <tr><td>Applied Possition : </td><td>${cvApplication.cvApplicationTemplate.cvHeaderEn} </td></tr>

          <c:forEach items="${cvApplication.cvApplicationTemplate.cvApplicationSectionList}" var="cvApplicationSectionList">
          <tr><td colspan="2">${cvApplicationSectionList.sectionNameEn}</td></tr>

              <c:forEach var="cvApplicationFieldList" items="${cvApplicationSectionList.cvApplicationFieldList}">
             <tr><td> ${cvApplicationFieldList.applicationFieldDictionary.labelEn}</td><td>  ${cvApplicationFieldList.fieldValue}</td></tr>
           </c:forEach>
       </c:forEach>
      </table>
      </legend>
   </fieldset>
</body>
</html>