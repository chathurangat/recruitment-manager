<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <script type="text/javascript" src="../resources/js/jquery.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.validate.js"></script>



    <script type="text/javascript">
        function myFunction()
        {
            alert("Not Completed")
            var image_path=Document.getElementById("vacancy_add_image");



        }


    </script>

</head>
<body>



<sf:form modelAttribute="/applicant/show"  action="/applicant/show" method="post" enctype="multipart/form-data">
    <fieldset>
        <legend>Vacancy......</legend>
        <br>
        <br>

        </br>
        </br>
        <div class="control-group">

            <div class="controls">
                <textarea  id="vacancy_add" name="vacancy_add" value="${adImage}" size="100"/>
            </div>
        </div>
        <a href="${cvTemplateId}">Apply</a>

    </fieldset>



</sf:form>



</body>
</html>
</jsp>