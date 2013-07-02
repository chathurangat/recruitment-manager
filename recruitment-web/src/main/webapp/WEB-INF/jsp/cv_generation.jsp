<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet"  type="text/css" />
    <link href="<c:url value="/resources/css/bootstrap-responsive.min.css" />" rel="stylesheet"  type="text/css" />
    <title></title>
    <style type="text/css">
        .formfield * {
            vertical-align: middle;
        }
    </style>

    <script type="text/javascript">
        $(document).ready(function(){

            $("#registerHere").validate({
                rules:{
                    First_Name : {
                        minlength: 2,
                        required: true
                    },
                    Last_Name : {
                        minlength: 2,
                        required: true
                    }
                },
                messages:{

                    First_Name:"Enter your first name",
                    Last_Name:"Enter your last name"
                },
                errorClass: "help-inline",
                errorElement: "span",
                highlight:function(element, errorClass, validClass) {
                    $(element).parents('.control-group').addClass('error');
                },
                unhighlight: function(element, errorClass, validClass) {
                    $(element).parents('.control-group').removeClass('error');
                    $(element).parents('.control-group').addClass('success');
                }
            });
        });
    </script>
   </head>
<body>

<sf:form method="POST"  id="registerHere" action="application_save"  modelAttribute="cvApplicationTemplate" class="form-horizontal">

    <sf:hidden path="id" />
    ${cvApplicationTemplate.cvHeaderEn}

    <c:forEach items="${cvApplicationTemplate.cvApplicationSectionList}" var="applicationSection">
        <fieldset name="${applicationSection.sectionNameEn}">
            <legend>
                    ${applicationSection.sectionNameEn}

            </legend>

            <c:forEach items="${applicationSection.cvApplicationFieldList}" var="applicationField">

                <c:set var="htmlComponent" value="${applicationField.applicationFieldDictionary.htmlComponent}"/>

                <c:if test="${fn:containsIgnoreCase(htmlComponent, 'textfield')}">

                    <label for="${applicationField.applicationFieldDictionary.id}">
                            ${applicationField.applicationFieldDictionary.labelEn}
                    </label>
                    <input id="${applicationField.applicationFieldDictionary.labelEn}" name="${applicationField.applicationFieldDictionary.labelEn}" type="text" size="${applicationField.applicationFieldDictionary.size}"/>
                    <br/>
                    <br/>
                </c:if>

                <c:if test="${fn:containsIgnoreCase(htmlComponent, 'textarea')}">

                    <p class="formfield">
                        <label for="${applicationField.applicationFieldDictionary.id}">
                                ${applicationField.applicationFieldDictionary.labelEn}
                        </label>
                        <textarea name="${applicationField.applicationFieldDictionary.labelEn}" id="${applicationField.applicationFieldDictionary.labelEn}" cols="${applicationField.applicationFieldDictionary.cols}" rows="${applicationField.applicationFieldDictionary.rows}"></textarea>
                    </p>
                    <br/>
                    <br/>
                </c:if>
            </c:forEach>
        </fieldset>
    </c:forEach>

    <div class="control-group">
        <div class="controls">
            <!--<input type="submit" class="btn btn-success"  value="<spring:message code="label.cvSection.registration.form.submit.button"/>"/>-->
            <input type="submit" class="btn btn-success"  value="Submit"/>
        </div>
    </div>

</sf:form>



<%--${cvApplicationTemplate.cvHeaderEn}--%>

<%--<c:forEach items="${cvApplicationTemplate.cvApplicationSectionList}" var="applicationSection">--%>
<%--<fieldset name="${applicationSection.sectionNameEn}">--%>
<%--<legend>--%>
<%--${applicationSection.sectionNameEn}--%>

<%--</legend>--%>

<%--<c:forEach items="${applicationSection.cvApplicationFieldList}" var="applicationField">--%>
<%--<c:set var="textField" value="${applicationField.applicationFieldDictionary.htmlComponent}"/>--%>

<%--<c:if test="${fn:containsIgnoreCase(textField, 'textfield')}">--%>
<%--<label for="${applicationField.applicationFieldDictionary.id}">--%>
<%--${applicationField.applicationFieldDictionary.label}--%>
<%--</label>--%>
<%--<input id="${applicationField.applicationFieldDictionary.id}" name="${applicationField.applicationFieldDictionary.id}" type="text" size="${applicationField.applicationFieldDictionary.size}"/>--%>
<%--<br/>--%>
<%--<br/>--%>
<%--</c:if>--%>

<%--<c:if test="${fn:containsIgnoreCase(textField, 'textarea')}">--%>
<%--<p class="formfield">--%>
<%--<label for="${applicationField.applicationFieldDictionary.id}">--%>
<%--${applicationField.applicationFieldDictionary.label}--%>
<%--</label>--%>
<%--<textarea name="${applicationField.applicationFieldDictionary.id}" cols="${applicationField.applicationFieldDictionary.cols}" rows="${applicationField.applicationFieldDictionary.rows}"></textarea>--%>
<%--</p>--%>
<%--<br/>--%>
<%--<br/>--%>
<%--</c:if>--%>
<%--</c:forEach>--%>
<%--</fieldset>--%>
<%--</c:forEach>--%>
<!-- Placed at the end of the document so the pages load faster -->
<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.0-min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/demo.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/date.format.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.validate.min.js" />"></script>

</body>
</html>