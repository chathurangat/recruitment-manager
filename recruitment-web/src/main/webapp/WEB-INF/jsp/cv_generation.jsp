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

<sf:form method="POST" action="application_save" id="registerHere" modelAttribute="cvApplicationTemplate" class="form-horizontal">

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
                    <input id="${applicationField.id}" name="${applicationField.id}" type="text" size="${applicationField.applicationFieldDictionary.size}"/>
                    <br/>
                    <br/>
                </c:if>

                <c:if test="${fn:containsIgnoreCase(htmlComponent, 'textarea')}">

                    <p class="formfield">
                        <label for="${applicationField.applicationFieldDictionary.id}">
                                ${applicationField.applicationFieldDictionary.labelEn}
                        </label>
                        <textarea name="${applicationField.id}" cols="${applicationField.applicationFieldDictionary.cols}" rows="${applicationField.applicationFieldDictionary.rows}"></textarea>
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
<script type="text/javascript" src="../resources/js/jquery.js" ></script>
<script type="text/javascript" src="../resources/js/jquery.validate.js" ></script>
<script type="text/javascript">
    $(document).ready(function(){

        $("#registerHere").validate({
            rules:{
                firstname:
                {
                    minlength: 2,
                    required: true
                },

                lastname : {
                    minlength: 2,
                    required: true
                },
                email : {
                    required: true,
                    email: true
                },
                contact_no :{
                    minlength: 10,
                    maxlength:10,
                    required: true
                },
                address : {
                    minlength: 2,
                    required: true
                },
                diploma_degree  : {
                    minlength: 2,
                    required: true
                },
                professional_certificates   : {
                    minlength: 2,
                    required: true
                },
                employment_history    : {
                    minlength: 2,
                    required: true
                },
                achievements     : {
                    minlength: 2,
                    required: true
                },
                programming_languages : {
                    minlength: 2,
                    required: true
                },
                operating_systems  : {
                    minlength: 2,
                    required: true
                },
                database_management_system   : {
                    minlength: 2,
                    required: true
                },
                ordinary_level : {
                    minlength: 2,
                    required: true
                },
                advanced_level : {
                    minlength: 2,
                    required: true
                },
                language_proficiency : {
                    minlength: 2,
                    required: true
                },
                sprots_activity : {
                    minlength: 2,
                    required: true
                },
                blogger_website  : {
                    minlength: 2,
                    required: true
                },
                hobbies   : {
                    minlength: 2,
                    required: true
                }
            },
            messages:{
                firstname:"Enter your first name",
                lastname : "Enter your last name",
                email : {
                    required:"Enter your email address",
                    email:"Enter valid email address"
                },
                contact_no :"Enter your contact Number ",
                address : "Enter your address",
                diploma_degree : "Enter your diploma / degree",
                professional_certificates : "Enter your professional certificates",
                employment_history : "Enter your employment history",
                achievements : "Enter your  achievements ",
                programming_languages : "Enter your programming languages",
                operating_systems : "Enter your operating systems",
                database_management_system : "Enter your database management system ",
                hobbies : "Enter your hobbies",
                blogger_website : "Enter your blogger or website id",
                sprots_activity : "Enter the details about sprots activity",
                language_proficiency : "Enter details about language proficiency",
                advanced_level : "Enter the advanced level (A/L) result ",
                ordinary_level : "Enter your ordinary level (O/L) result"

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

</body>
</html>