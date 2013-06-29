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

<sf:form method="POST" action="applicationSubmit"  modelAttribute="cvApplicationSection" class="form-horizontal">

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

</body>
</html>