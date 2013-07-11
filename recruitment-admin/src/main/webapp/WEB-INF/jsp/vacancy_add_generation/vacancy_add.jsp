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



    <sf:form modelAttribute="vacancy_add_generate"  action="vacancy_add_generate" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Generate Vacancy Advertiesment Here....</legend>
            <br>
            <br>

            </br>
            </br>
            <div class="control-group">
                <label class="control-label" for="vacancy_add_image">Upload your vacancy addvertiesment as an image file here....</label>
                <div class="controls">
                    <input type="file"  id="vacancy_add_image" name="vacancy_add_image" size="100"/>


                </div>

            </div>


        </fieldset>

        <fieldset>
            <legend></legend>

            <label class="radio" id="cvTemplate_selection">


                <ul class="nav nav-list">
                    <li class="nav-header">Select the Cv Application here !</li>

                    <c:forEach items="${cvApplicationTemplate}" var="cvApplicationTemplate">
                        <input type="radio" name="optionsRadios" id="${cvTemplate_selection.cvApplicationTemplate.cvHeaderEn}" value="${cvApplicationTemplate.cvHeaderEn}"/>
                        <li><label>${cvApplicationTemplate.cvHeaderEn}</label></li>
                    </c:forEach>
                </ul>
            </label>

        </fieldset>

        <div class="controls">
            <button type="submit" name="submit" class="btn btn-success "  id="submit" onclick="myFunction()">Submit</button>
        </div>

    </sf:form>



    </body>
    </html>
</jsp>