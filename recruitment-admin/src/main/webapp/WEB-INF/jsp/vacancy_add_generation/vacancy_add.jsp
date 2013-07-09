<jsp version="2.0" xmlns:jsp="http://java.sun.com/JSP/Page" xmlns:sf="http://www.springframework.org/tags/form"
          xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:fn="http://java.sun.com/jsp/jstl/functions">
    <jsp:directive.page contentType="text/html;charset=UTF-8" language="java"/>
    <html>
    <head>
        <title><![CDATA[<!---->]]></title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript" src="../resources/js/jquery.js"></script>
        <script type="text/javascript" src="../resources/js/jquery.validate.js"></script>



        <script type="text/javascript">
            function myFunction()
            {
                alert("I am an alert box!");
            }


        </script>


    </head>
    <body>



    <sf:form modelAttribute="uploadItem" method="post" enctype="multipart/form-data">
        <fieldset>
            <legend>Generate Vacancy Advertiesment Here....</legend>
            <br>
            <br>

            </br>
            </br>
            <div class="control-group">
                <label class="control-label" for="vacancy_add">Upload your vacancy addvertiesment as an image file here....</label>
                <div class="controls">
                    <input type="text"  id="vacancy_add" name="vacancy_add" size="100"/>

                    <button type="submit" name="upload" class="btn "  id="upload" >Upload</button>
                </div>

            </div>


        </fieldset>

        <fieldset>
            <legend></legend>

            <label class="radio" id="update_seclection">


                <ul class="nav nav-list">
                    <li class="nav-header">Select the Cv Application here !</li>

                    <c:forEach items="${cvApplicationTemplate}" var="cvApplicationTemplate">
                        <input type="radio" name="optionsRadios" id="${cvApplicationTemplate.cvHeaderEn}" value="${cvApplicationTemplate.cvHeaderEn}"/>
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