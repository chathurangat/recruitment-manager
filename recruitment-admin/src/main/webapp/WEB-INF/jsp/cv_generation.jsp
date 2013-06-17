<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

${cvApplicationTemplate.cvHeader}

<c:forEach items="${cvApplicationTemplate.cvApplicationSectionList}" var="applicationSection">
    <fieldset name="${applicationSection.sectionName}">
        <legend>
                ${applicationSection.sectionName}

        </legend>

        <c:forEach items="${applicationSection.cvApplicationFieldList}" var="applicationField">
            <c:set var="textField" value="${applicationField.applicationFieldDictionary.htmlComponent}"/>

            <c:if test="${fn:containsIgnoreCase(textField, 'textfield')}">
                <label for="${applicationField.applicationFieldDictionary.id}">
                        ${applicationField.applicationFieldDictionary.label}
                </label>
                <input id="${applicationField.applicationFieldDictionary.id}" name="${applicationField.applicationFieldDictionary.id}" type="text" size="${applicationField.applicationFieldDictionary.size}"/>
                <br/>
                <br/>
            </c:if>

            <c:if test="${fn:containsIgnoreCase(textField, 'textarea')}">
                <p class="formfield">
                    <label for="${applicationField.applicationFieldDictionary.id}">
                            ${applicationField.applicationFieldDictionary.label}
                    </label>
                    <textarea name="${applicationField.applicationFieldDictionary.id}" cols="${applicationField.applicationFieldDictionary.cols}" rows="${applicationField.applicationFieldDictionary.rows}"></textarea>
                </p>
                <br/>
                <br/>
            </c:if>
        </c:forEach>
    </fieldset>
</c:forEach>

</body>
</html>