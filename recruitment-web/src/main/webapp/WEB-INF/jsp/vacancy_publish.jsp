<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<sf:form modelAttribute="" action="/applicant/show" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>Vacancy......</legend>
        <br>

        <div class="control-group">
           <div class="controls">
               <ul class="nav nav-list">
                   <li class="nav-header">Select the vacancy here !</li>
                       <c:forEach items="${vacancyList}" var="vacancyList">
                        <li>
                            <img src="${vacancyList.filePath}" alt="vacancy_image"/>
                            <label>
                                <a href="http://rec.admin.hsenidmobile.com:8080/recruitment-web/user/applicant/apply?id=${vacancyList.cvApplicationTemplateId}">apply now !</a>
                            </label>
                        </li>
                       </c:forEach>
               </ul>
            </div>
        </div>

   </fieldset>

</sf:form>
</jsp>