<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<fieldset>
    <legend>
        <spring:message code="label.form.header.cvSection.registration"/>
    </legend>
</fieldset>

<sf:form method="POST" action="register"  modelAttribute="cvApplicationSection" class="form-horizontal">

    <div class="control-group">
        <div class="controls">
            <sf:label path="sectionNameEn">
                <spring:message code="label.cvSection.registration.form.name.in.english"/>
            </sf:label>
            <sf:input path="sectionNameEn" size="100" />
            <sf:errors path="sectionNameEn" cssClass="text-error"/>
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <sf:label path="sectionNameSi">
                <spring:message code="label.cvSection.registration.form.name.in.sinhala"/>
            </sf:label>
            <sf:input path="sectionNameSi" size="100" />
            <sf:errors path="sectionNameSi" cssClass="text-error"/>
        </div>
    </div>

    <div class="control-group">
        <div class="controls">
            <sf:label path="sectionNameTa">
                <spring:message code="label.cvSection.registration.form.name.in.tamil"/>
            </sf:label>
            <sf:input path="sectionNameTa" size="100" />
            <sf:errors path="sectionNameTa" cssClass="text-error"/>
        </div>
    </div>


    <div class="control-group">
        <div class="controls">
               <input type="submit" class="btn btn-success"  value="<spring:message code="label.cvSection.registration.form.submit.button"/>"/>
        </div>
    </div>

</sf:form>
